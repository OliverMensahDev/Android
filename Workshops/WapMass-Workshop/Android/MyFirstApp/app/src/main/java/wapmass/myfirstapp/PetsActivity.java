package wapmass.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class PetsActivity extends AppCompatActivity {
    //First get instances of the views from your layout
    private ImageView cat, dog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pets);

        //find the views with the help  of their ids
        dog = findViewById(R.id.dog);
        cat = findViewById(R.id.cat);

        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetsActivity.this, PetDetailsActivity.class);
                intent.putExtra("name", "My Dog");
                intent.putExtra("description", "I have a red dog bought from Mr. Abeam at the Kumasi Central Market last four years ago and I love it");
                startActivity(intent);
            }
        });

        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetsActivity.this, PetDetailsActivity.class);
                intent.putExtra("name", "My Cat");
                intent.putExtra("description", "I have a red cat bought from Mr. Abeam at the Kumasi Central Market last four years ago and I love it");
                startActivity(intent);
            }
        });

    }
}
