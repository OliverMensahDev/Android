package wapmass.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

import wapmass.listview.Data.LandScapeData;
import wapmass.listview.Model.LandScape;

public class DetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView title, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        LandScape landScape = LandScapeData.getData().get(bundle.getInt("index"));
        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        image.setImageResource(landScape.getImageId());
        title.setText(landScape.getTitle());
        description.setText(landScape.getDescription());


    }
}
