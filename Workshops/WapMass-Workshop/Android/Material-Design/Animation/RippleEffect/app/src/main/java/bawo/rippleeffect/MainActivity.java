package bawo.rippleeffect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindControl();
    }

    private void bindControl() {
        TextView txvRippleWithBorder = findViewById(R.id.txvCustomRippleWithBorder);
        TextView txvRippleWithoutBorder  = findViewById(R.id.txvCustomRippleWithoutBorder);
        TextView txvCustomRippleWithBorder = findViewById(R.id.txvCustomRippleWithBorder);
        TextView txvCustomRippleWithoutBorder = findViewById(R.id.txvCustomRippleWithoutBorder);

        txvRippleWithBorder.setOnClickListener(this);
        txvRippleWithoutBorder.setOnClickListener(this);
        txvCustomRippleWithBorder.setOnClickListener(this);
        txvCustomRippleWithoutBorder.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.explodeJava: {

                break;
            }

            case R.id.explodeXML: {

                break;
            }

            case R.id.slideJava : {

                break;
            }

            case R.id.slideXML : {

                break;
            }

            case R.id.fadeJava : {

                break;
            }

            case R.id.fadeXML : {

                break;
            }

            case R.id.shared_element : {

                break;
            }
        }
    }
}
