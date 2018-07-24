package bawo.photoshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private int GALLERY_REQUEST_CODE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    //butternknike for click event
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @OnClick(R.id.main_button_take_photo)
    public  void onTakePhotoClicked(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "SELECT PICTURE"), GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_REQUEST_CODE){
            if(resultCode == RESULT_OK && data != null){
                byte[] pictureContent = loadImage(data.getData());
                if(pictureContent != null){

                }
            }
        }
    }

    private byte[] loadImage(Uri pathToImage){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream fileInputStream = null;
        try {
            fileInputStream = getContentResolver().openInputStream(pathToImage);
            byte[] buffer = new byte[1024];

            while(fileInputStream.read(buffer) != -1)
                outputStream.write(buffer, 0, buffer.length);
            return outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            if(fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    //butternife for reference
//    @BindView(R.id.main_button_take_photo)
//    Button mBttuon;
}
