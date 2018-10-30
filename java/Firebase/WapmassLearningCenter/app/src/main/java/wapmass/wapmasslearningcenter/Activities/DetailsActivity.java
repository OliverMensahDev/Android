package wapmass.wapmasslearningcenter.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import wapmass.wapmasslearningcenter.Model.Course;
import wapmass.wapmasslearningcenter.R;

public class DetailsActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView title, body;
    private FloatingActionButton fab;
    private  Course course;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        setupViews();



    }

    private void setupViews(){
        imageView = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        body = findViewById(R.id.detail_body);


        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            course =  (Course) getIntent().getSerializableExtra("clickedArticle");
            Picasso.get().load(course.getImageLink()).into(imageView);
            title.setText(course.getcTitle());
            body.setText(course.getcDesc());
        }

    }
}
