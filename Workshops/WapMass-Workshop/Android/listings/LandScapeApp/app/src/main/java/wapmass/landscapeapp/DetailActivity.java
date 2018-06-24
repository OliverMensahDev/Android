package wapmass.landscapeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import wapmass.landscapeapp.Data.LandScapeData;
import wapmass.landscapeapp.Model.LandScape;

public class DetailActivity extends AppCompatActivity {
    private ImageView image;
    private TextView title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        //get my intent
        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            //get my data from the landscapedata using the index
            int position   = bundle.getInt("index");
            //arraylist = get put se
            LandScape landScape = LandScapeData.getData().get(position);
            title.setText(landScape.getTitle());
            image.setImageResource(landScape.getImageId());
            description.setText(landScape.getDescription());
        }
    }
}
