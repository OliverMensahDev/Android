package wapmass.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import wapmass.myfirstapp.QuoteServer.Server;

public class Quotes extends AppCompatActivity {
    private Button btn;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        text = findViewById(R.id.textQA);
        btn = findViewById(R.id.btnQA);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Server server = new Server();
                text.setText(server.getRandomQuote());
            }
        });

    }
}
