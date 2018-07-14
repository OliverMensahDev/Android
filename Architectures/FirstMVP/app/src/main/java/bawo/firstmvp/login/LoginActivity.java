package bawo.firstmvp.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import javax.inject.Inject;

import bawo.firstmvp.R;
import bawo.firstmvp.root.App;

public class LoginActivity extends AppCompatActivity implements  LoginActivityMVP.View {

    @Inject
    LoginActivityMVP.Presenter presenter;

    private EditText firstName;
    private EditText lastName;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);

        firstName = findViewById(R.id.loginActivity_firstName_editText);
        lastName = findViewById(R.id.loginActivity_secondName_editText);
        login = findViewById(R.id.loginActivity_login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginButtonClicked();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return firstName.getText().toString();
    }

    @Override
    public String getSecondName() {
        return lastName.getText().toString();
    }

    @Override
    public void setFirstName(String fname) {
        firstName.setText(fname);
    }

    @Override
    public void setLastName(String lname) {
        lastName.setText(lname);

    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Firstname or LastName cannot be empty", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showSavedMessage() {
        Toast.makeText(this, "U" +
                "User is saved", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNotAvailableUSer() {
        Toast.makeText(this, "Error the user is not available", Toast.LENGTH_LONG).show();
    }
}
