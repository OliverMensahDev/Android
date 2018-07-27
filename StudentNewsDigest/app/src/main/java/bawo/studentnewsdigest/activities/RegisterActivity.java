package bawo.studentnewsdigest.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import bawo.studentnewsdigest.R;
import bawo.studentnewsdigest.model.User;
import bawo.studentnewsdigest.util.FirebaseUtil;
import bawo.studentnewsdigest.util.InputUtil;

public class RegisterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private EditText emailEditText, passwordEditText, fullNameEditText;
    private TextInputLayout emailTextInputLayout, passwordTextInputLayout, fullNameTextInputLayout;
    private Button regButton;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setUpWidgets();

    }

    public void setUpWidgets(){
        toolbar =  findViewById(R.id.toolbar);
        emailEditText = findViewById(R.id.reg_editText_email);
        passwordEditText = findViewById(R.id.reg_editText_password);
        fullNameEditText = findViewById(R.id.reg_editText_fullname);
        emailTextInputLayout = findViewById(R.id.reg_inputLayoutEmail);
        passwordTextInputLayout = findViewById(R.id.reg_inputLayoutPassword);
        fullNameTextInputLayout = findViewById(R.id.reg_inputLayoutFullName);
        regButton =  findViewById(R.id.reg_button_register);
        progressBar = findViewById(R.id.reg_progressBar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String fullname = fullNameEditText.getText().toString();
                if(InputUtil.validateInput(email, emailTextInputLayout, "Enter Email" ) &&
                        InputUtil.isValidEmail(email, emailTextInputLayout, "Enter a Valid Email") &&
                        InputUtil.validateInput(password, passwordTextInputLayout, "Enter Password") &&
                        InputUtil.validateInput(fullname, fullNameTextInputLayout, "Enter Full Name")
                        ){
                    if(servicesOK()){
                        createNewAccount(view, email, password, fullname);
                    }else{
                        Snackbar snackbar = Snackbar.make(view,"Bad Internet Connection", Snackbar.LENGTH_LONG);
                        snackbar.setAction("RETRY", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(new Intent(RegisterActivity.this, RegisterActivity.class));
                            }
                        });
                        snackbar.show();
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onSupportNavigateUp() {
        finishAfterTransition();
        return true;
    }

    private void createNewAccount(final View view, final String email, String password, final String fullname) {
        showDialog();
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
            FirebaseUtil.setupAuth().createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (authResult != null) {
                                hideDialog();
                                User user = new User();
                                user.setEmail(email);
                                user.setName(fullname);
                                saveUser(user);
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    hideDialog();
                    Snackbar snackbar = Snackbar.make(view,e.getMessage(), Snackbar.LENGTH_LONG);
                    snackbar.setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(RegisterActivity.this, RegisterActivity.class));
                        }
                    });
                    snackbar.show();
                }
            });

        }
    }

    public boolean servicesOK(){

        int isAvailable = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(RegisterActivity.this);

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

    private void showDialog(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideDialog(){
        if(progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private boolean sendVerificationEmail() {
        final boolean[] isSuccessful = {false};
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Account Saved! \n Verification Email Sent, Check your email", Toast.LENGTH_SHORT).show();
                                FirebaseUtil.setupAuth().signOut();
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, task.getException().getMessage()+ "! Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(RegisterActivity.this, "no user", Toast.LENGTH_SHORT).show();
        }
        return  isSuccessful[0];

    }

    public void saveUser(User user){
        FirebaseUtil.setupDatabase("users").child(FirebaseUtil.setupAuth().getCurrentUser().getUid()).setValue(user).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            sendVerificationEmail();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

}
