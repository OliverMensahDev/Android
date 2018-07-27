package bawo.studentnewsdigest.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import bawo.studentnewsdigest.R;
import bawo.studentnewsdigest.util.FirebaseUtil;
import bawo.studentnewsdigest.util.InputUtil;

public class LoginActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextInputLayout emailTextInputLayout, passwordTextInputLayout;
    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView resetPasswordLink, registerLink, resendConfirmationLink;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        hideSoftKeyboard();
        setupWidgets();
        setUpEVentListeners();
    }
    private void setupWidgets(){
        emailTextInputLayout =  findViewById(R.id.inputLayoutEmail);
        passwordTextInputLayout = findViewById(R.id.inputLayouPassword);
        emailEditText = findViewById(R.id.main_editText_email);
        passwordEditText = findViewById(R.id.main_editText_password);
        loginButton = findViewById(R.id.main_button_login);
        resetPasswordLink = findViewById(R.id.main_textView_forgot_password);
        resendConfirmationLink = findViewById(R.id.main_textView_resend_verification_email);
        registerLink = findViewById(R.id.main_textView_link_register);
        progressBar = findViewById(R.id.login_progressBar);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
    }

    private void setUpEVentListeners(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(InputUtil.validateInput(email, emailTextInputLayout, "Enter Email" ) &&
                        InputUtil.isValidEmail(email, emailTextInputLayout, "Enter a Valid Email") &&
                        InputUtil.validateInput(password, passwordTextInputLayout, "Enter Password")
                        ){
                    if(servicesOK()){
                        login(email, password);
                    }else{
                        Snackbar snackbar = Snackbar.make(view,"Bad Internet Connection", Snackbar.LENGTH_LONG);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                            }
                        });
                        snackbar.show();
                    }
                }

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
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
        if(isRemeberedMe()){
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }
        FirebaseUser user = FirebaseUtil.setupAuth().getCurrentUser();
        if(user != null) {
            if(user.isEmailVerified()){
                startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                finish();
            }
        }

    }
    private void login(String email, String password) {
        showDialog();
        FirebaseUtil.setupAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = FirebaseUtil.setupAuth().getCurrentUser();
                            if (currentUser != null) {
                                if(currentUser.isEmailVerified()) {
                                    hideDialog();
                                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                                    finish();
                                }else{
                                    hideDialog();
                                    setuppRememberMe();
                                    Toast.makeText(LoginActivity.this, "Please Make sure your email is verified ", Toast.LENGTH_LONG).show();
                                }
                            }
                        } else {
                            hideDialog();
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showDialog(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideDialog(){
        if(progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }



    public boolean servicesOK(){

        int isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LoginActivity.this);

        if(isAvailable == ConnectionResult.SUCCESS){
            //everything is ok and the user can make mapping requests
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(isAvailable)){
            //an error occured, but it's resolvable
            Toast.makeText(this, "Can't connect to mapping services", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Can't connect to mapping services", Toast.LENGTH_SHORT).show();
        }
        return false;
    }


    private void setuppRememberMe(){
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("loggedIn", "true");
        editor.apply();

    }

    private boolean isRemeberedMe(){
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String rememberMe = sharedPreferences.getString("loggedIn", "N/A");
        return rememberMe =="true" ;
    }

}

