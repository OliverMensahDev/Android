package bawo.nationalstudentnewspaper;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputLayout emailTextInputEditText, passwordTextInputEditText;
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView resetPasswordLink, registerLink, resendConfirmationLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        hideSoftKeyboard();
        setupWidgets();
        setUpEVentListeners();
    }
    private void setupWidgets(){
        emailTextInputEditText =  findViewById(R.id.inputLayoutEmail);
        passwordTextInputEditText = findViewById(R.id.inputLayouPassword);
        emailEditText = findViewById(R.id.main_editText_email);
        passwordEditText = findViewById(R.id.main_editText_password);
        loginButton = findViewById(R.id.main_button_login);
        resetPasswordLink = findViewById(R.id.main_textView_forgot_password);
        resendConfirmationLink = findViewById(R.id.main_textView_resend_verification_email);
        registerLink = findViewById(R.id.main_textView_link_register);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("National Student Newspaper");
        setSupportActionBar(toolbar);
    }

    private void setUpEVentListeners(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                authenticate(email,password);
            }
        });


        resetPasswordLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgottenPasswordPopup();
            }
        });

        resendConfirmationLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendVerificationPopup();
            }
        });


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    private boolean validateInput(String input, TextInputLayout inputEditText, String errorMessage) {
        if(input.isEmpty()){
            inputEditText.setError(errorMessage);
            return false;
        }else
        {
            inputEditText.setErrorEnabled(false);
            return true;
        }
    }

    private boolean isValidEmail(String email){
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailTextInputEditText.setError("Enter Valid Email");
            return  false;
        }else{
            passwordTextInputEditText.setErrorEnabled(false);
            return true;
        }
    }


    private void authenticate(String email, String password){
        if(validateInput(email, emailTextInputEditText, "Enter Email" ) &&
                isValidEmail(email) &&
                validateInput(password, passwordTextInputEditText, "Enter Password")){

        }
    }


    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    private void forgottenPasswordPopup() {

        AlertDialog.Builder dialogBuilder  = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_resetpassword, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        Button sendEmail = view.findViewById(R.id.dialog_button_send);

        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

    }

    private void resendVerificationPopup() {

        AlertDialog.Builder dialogBuilder  = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_resend_verification, null);
        dialogBuilder.setView(view);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

    }
}
