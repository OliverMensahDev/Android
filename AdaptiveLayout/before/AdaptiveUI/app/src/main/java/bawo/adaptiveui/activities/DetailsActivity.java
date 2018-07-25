package bawo.adaptiveui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bawo.adaptiveui.R;
import bawo.adaptiveui.data.PostData;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        TextView textView = findViewById(R.id.description);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            textView.setText(PostData.postList().get(bundle.getInt("position")).getPostDescription());
        }
    }
}
