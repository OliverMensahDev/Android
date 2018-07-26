package bawo.studentnewsdigest.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import bawo.studentnewsdigest.R;
import bawo.studentnewsdigest.model.Article;
import bawo.studentnewsdigest.util.FirebaseUtil;

public class PostArticle extends AppCompatActivity {
    private static final int GALLERY_CODE = 1;
    private Toolbar toolbar;
    private EditText title, body;
    private Button save;
    private ImageButton imageButton;
    private ProgressBar progressBar;
    private Uri mImageUri;
    private static final double MB_THRESHHOLD = 5.0;
    private static final double MB = 1000000.0;
    private byte[] mBytes;
    private double progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_article);
        setUpWidgets();
    }

    public void setUpWidgets() {
        toolbar = findViewById(R.id.post_toolbar);
        progressBar = findViewById(R.id.post_progressBar);
        imageButton = findViewById(R.id.post_imageButton);
        title = findViewById(R.id.post_TitleEt);
        body = findViewById(R.id.post_descriptionEt);
        save = findViewById(R.id.post_button_post);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mImageUri != null){
                    uploadNewPhoto(mImageUri);
                }
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onSupportNavigateUp() {
        finishAfterTransition();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            mImageUri = data.getData();
            if(mImageUri != null){
                imageButton.setImageURI(mImageUri);
            }else{
                Toast.makeText(PostArticle.this, "Please Select an Image", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void uploadNewPhoto(Uri imageUri){
        BackgroundImageResize resize = new BackgroundImageResize(null);
        resize.execute(imageUri);
    }

    public class BackgroundImageResize extends AsyncTask<Uri, Integer, byte[]> {
        Bitmap mBitmap;
        public BackgroundImageResize(Bitmap bm) {
            if(bm != null){
                mBitmap = bm;
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog();
            Toast.makeText(PostArticle.this, "compressing image", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected byte[] doInBackground(Uri... params ) {
              if(mBitmap == null){
                try {
                    mBitmap = MediaStore.Images.Media.getBitmap(PostArticle.this.getContentResolver(), params[0]);
                } catch (IOException e) {
                    Log.e("Uploading", "doInBackground: IOException: ", e.getCause());
                }
            }
            byte[] bytes = null;
            for (int i = 1; i < 11; i++){
                if(i == 10){
                    Toast.makeText(PostArticle.this, "That image is too large.", Toast.LENGTH_SHORT).show();
                    break;
                }
                bytes = getBytesFromBitmap(mBitmap,100/i);
                if(bytes.length/MB  < MB_THRESHHOLD){
                    return bytes;
                }
            }
            return bytes;
        }

        @Override
        protected void onPostExecute(byte[] bytes) {
            super.onPostExecute(bytes);
            hideDialog();
            mBytes = bytes;
            executeUploadTask();
        }
    }

    public static byte[] getBytesFromBitmap(Bitmap bitmap, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, stream);
        return stream.toByteArray();
    }

    private void executeUploadTask() {
        showDialog();
        final String titleVal = title.getText().toString().trim();
        final String descVal = body.getText().toString().trim();
        if (mBytes.length / MB < MB_THRESHHOLD) {
            final StorageReference filepath = FirebaseUtil.setStorage("article_images")
                    .child(mImageUri.getLastPathSegment());
            filepath.putBytes(mBytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadurl = taskSnapshot.getDownloadUrl();
                    DatabaseReference newPost = FirebaseUtil.setupDatabase("articles").push();
                    Article article = new Article();
                    article.setTitle(titleVal);
                    article.setDetails(descVal);
                    article.setImage(downloadurl.toString());
                    newPost.setValue(article)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    hideDialog();
                                    Toast.makeText(PostArticle.this, "Post Added", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(PostArticle.this, DashboardActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            hideDialog();
                            Toast.makeText(PostArticle.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double currentProgress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                    if(currentProgress > (progress + 15)){
                        progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        Toast.makeText(PostArticle.this, progress + "%", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Image is too Large", Toast.LENGTH_SHORT).show();
        }
    }

    private void showDialog(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideDialog(){
        if(progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

}
