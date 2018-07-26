package bawo.studentnewsdigest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bawo.studentnewsdigest.R;
import bawo.studentnewsdigest.model.Article;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView title, body;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupViews();



    }

    private void setupViews(){
        imageView = findViewById(R.id.detail_image);
        title = findViewById(R.id.detail_title);
        body = findViewById(R.id.detail_body);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            Article article =  (Article) getIntent().getSerializableExtra("clickedArticle");
            Picasso.get().load(article.getImage()).into(imageView);
            title.setText(article.getTitle());
            body.setText(article.getDetails());
        }

    }
}

