package wapmass.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText enteredAmout;
    private SeekBar seekBar;
    private Button calculateButton;
    private TextView totalResultTextView;
    private TextView textViewSeekbar;
    private int seekbarPercentage;
    private float enteredBillFloat;
    private TextView totalBillTv;

    public static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enteredAmout = (EditText) findViewById(R.id.billAmountID);
        seekBar = (SeekBar) findViewById(R.id.percentageSeekBar);
        calculateButton = (Button) findViewById(R.id.calculateButton);
        totalResultTextView = (TextView) findViewById(R.id.resultID);
        textViewSeekbar = (TextView) findViewById(R.id.textViewSeekbar);
        totalBillTv = (TextView) findViewById(R.id.totalBillTextView);

        Toast.makeText(getApplication(), "Hello", Toast.LENGTH_LONG).show();


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float result =  0.0f;

                if (!enteredAmout.getText().toString().equals("")) {

                    enteredBillFloat = Float.parseFloat(enteredAmout.getText().toString());
                    result = enteredBillFloat * seekbarPercentage / 100;
                    totalResultTextView.setText("You tip will be" + " $"+String.valueOf(result) );
                    totalBillTv.setText("Total bill: " + " $"+String.valueOf(enteredBillFloat + result));

                    Log.v(TAG, String.valueOf(result));

                }else {
                    Toast.makeText(MainActivity.this, "Please enter a bill amount.", Toast.LENGTH_LONG).show();
                }
            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textViewSeekbar.setText(String.valueOf(seekBar.getProgress()) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                seekbarPercentage = seekBar.getProgress();
            }
        });
    }
}
