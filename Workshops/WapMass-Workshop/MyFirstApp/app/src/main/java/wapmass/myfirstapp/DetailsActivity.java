package wapmass.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView emailPlaceHolder, namePlaceHolder, agePlaceHolder, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        emailPlaceHolder = findViewById(R.id.EmailPlaceHolder_DA);
        namePlaceHolder = findViewById(R.id.NamePlaceHolder_DA);
        agePlaceHolder = findViewById(R.id.AgePlaceHolder_DA);
        phone = findViewById(R.id.PhonePlaceHolder_DA);
        // using bundle to retrieve content
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("email");
        bundle.getString("name");
        bundle.getString("age");

        // Using Extra for the data
        getIntent().getStringExtra("email");

        // using string method on extras
        getIntent().getExtras().getString("email");

        emailPlaceHolder.setText(bundle.getString("email"));
        namePlaceHolder.setText(name + " Another word");
        agePlaceHolder.setText(getIntent().getExtras().getString("age"));
        phone.setText(getIntent().getStringExtra("phone"));

    }
}
