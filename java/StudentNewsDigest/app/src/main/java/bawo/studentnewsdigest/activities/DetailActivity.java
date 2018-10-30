package bawo.studentnewsdigest.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import bawo.studentnewsdigest.R;
import bawo.studentnewsdigest.model.Article;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView title, body;
    private FloatingActionButton fab;
    private  Article article;



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
        fab = findViewById(R.id.share);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
           article =  (Article) getIntent().getSerializableExtra("clickedArticle");
            Picasso.get().load(article.getImage()).into(imageView);
            title.setText(article.getTitle());
            body.setText(article.getDetails());
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = article.getTitle() + "\n\n "+
                        article.getDetails().substring(0, 100) +"..."
                        + "\n\n Interested in Getting Student News In Ghana? Download Student News Digest from Playstore"
                        ;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

    }
}

