package bawo.com.databinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // inflate layout
        setContentView(R.layout.activity_main);

        //find layout element and assign to a local variable
        TextView hello = findViewById(R.id.hello);

        //Get data
        String data = "hello";

        // Assign value to element property
        hello.setText(data);


    }
}
