package wapmass.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String TAG ="MainActivity";
    // Get the widgets instance
    private Button submitButton, gps;
    private EditText name, email, age, phone, house;

 // TODO: ADd Cpded
    @Override
    protected void onCreate(Bundle me) {
        super.onCreate(me);
        setContentView(R.layout.activity_main);

        submitButton = findViewById(R.id.sSubmitBtn_L);

        name = findViewById(R.id.editTxtName_LL);
        email = findViewById(R.id.email_LL);
        age = findViewById(R.id.age_LL);
        gps = findViewById(R.id.GPS_LL);
        phone = findViewById(R.id.PhoneNumber_LL);
        house = findViewById(R.id.HouseNumber_LL);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredAge = age.getText().toString();
                String enteredName = name.getText().toString();
                String enteredEmail = email.getText().toString();
                String houseNumber  = house.getText().toString();
                String gps = "0.90, 0202190";
                String phoneNumber = phone.getText().toString();

                if(enteredAge.isEmpty() || enteredName.isEmpty() || enteredEmail.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please fill in the details", Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    intent.putExtra("email", enteredEmail);
                    intent.putExtra("age", enteredAge);
                    intent.putExtra("name", enteredName);
                    intent.putExtra("phone", phoneNumber);

                    startActivity(intent);
                }
            }
        });

        submitButton = findViewById(R.id.btnCancel_LL);





    }
}
