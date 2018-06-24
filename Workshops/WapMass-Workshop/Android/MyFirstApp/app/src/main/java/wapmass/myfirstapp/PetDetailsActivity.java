package wapmass.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


public class PetDetailsActivity extends AppCompatActivity {
    //First get the instance of the views from your layout
    private TextView name, details;
    private ImageView myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);

        // find them with the help of their ids
        name = findViewById(R.id.animalName);
        details = findViewById(R.id.animalDetails);
        myImage = findViewById(R.id.animalImage);

        //  Retrieving intent content from the other screen with the given key
        String nameFromIntent = getIntent().getStringExtra("name");

        if(nameFromIntent.equals("My Dog")){
            name.setText(getIntent().getStringExtra("name"));
            details.setText(getIntent().getStringExtra("description"));
            myImage.setImageDrawable(getResources().getDrawable(R.drawable.icon_lg_dog));
        }else{
            name.setText(getIntent().getStringExtra("name"));
            details.setText(getIntent().getStringExtra("description"));
            myImage.setImageDrawable(getResources().getDrawable(R.drawable.icon_lg_cat));
        }
    }
}
