package wapmass.wapmasslearningcenter.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import wapmass.wapmasslearningcenter.R;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private SharedPreferences sharedPreferences;
    private CheckBox checkBox;

    private EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        checkBox = findViewById(R.id.checked);
        addLoginData();

        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String data = "";
                if(checkBox.isChecked()){
                    data = "student";
                }else{
                    data = "admin";
                }
               if(login(user, pass, checkBox.isChecked())){
                   Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                   intent.putExtra("user", data);
                   startActivity(intent);
               }else{
                   Toast.makeText(LoginActivity.this, "Login With Your Correct Credentials", Toast.LENGTH_SHORT).show();
               }
            }
        });

    }

    private void addLoginData(){
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("username", "admin");
        editor.putString("password", "admin");

        editor.putString("stuName", "student");
        editor.putString("stuPass", "student");

        editor.apply();

    }

    private boolean login(String username, String password, boolean isChecked){
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String adminName = sharedPreferences.getString("username", "N/A");
        String adminPass = sharedPreferences.getString("password", "N/A");
        String stuName = sharedPreferences.getString("stuName", "N/A");
        String stuPass = sharedPreferences.getString("stuPass", "N/A");
        return isChecked ? stuName.equals(username) && stuPass.equals(password): (adminName.equals(username) && adminPass.equals(password)) ;
    }
}
