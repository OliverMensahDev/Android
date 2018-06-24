package wapmass.wapmasslearningcenter.Activities;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import wapmass.wapmasslearningcenter.Data.DatabaseHandler;
import wapmass.wapmasslearningcenter.Model.Course;
import wapmass.wapmasslearningcenter.R;

public class AddCourse extends AppCompatActivity {
    private EditText Ctitle, Cdescription, reglink;
    private Button Savebtn;
    private ImageButton CImage;
    private Uri imageUri;
    private Bitmap courseImage;
    private static final int IMAGE_REQUEST_CODE = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Ctitle = findViewById(R.id.coursetitle);
        Cdescription = findViewById(R.id.coursedetails);
        reglink = findViewById(R.id.reglink);

        Savebtn = findViewById(R.id.savecourse);
        CImage = findViewById(R.id.courselogo);

        CImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent, IMAGE_REQUEST_CODE);
            }
        });
        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course course = new Course();
                // TODO:: save image in drawable and get the link to be saved in sqlite
                //course.setcImage(profileImage(courseImage).toString());
                course.setcDesc(Cdescription.getText().toString());
                course.setcTitle(Ctitle.getText().toString());
                course.setcLink(reglink.getText().toString());
                if(saveCourse(course)){
                    Toast.makeText(AddCourse.this, "Saved", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCourse.this, DashboardActivity.class);
                    intent.putExtra("user", "admin");
                    startActivity(intent);
                }else{
                    Toast.makeText(AddCourse.this, "Not Saved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean saveCourse(Course course){
        DatabaseHandler db = new DatabaseHandler(this);
        return db.addCourse(course);
    }

}
