package wapmass.landscaperecyclerview;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import wapmass.landscaperecyclerview.Data.LandScapeData;
import wapmass.landscaperecyclerview.Model.LandScape;

public class DetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView title, description;
    private LandScape landScape;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        //get clicked item data from intent key
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            landScape = LandScapeData.getData().get(bundle.getInt("index"));
            image.setImageResource(landScape.getImageId());
            title.setText(landScape.getTitle());
            description.setText(landScape.getDescription());
        }
        setupToolbar();
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(landScape.getTitle());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onSupportNavigateUp() {
        finishAfterTransition();
        return true;
    }
}
