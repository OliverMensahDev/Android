package com.bawo.whatsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private EditText phoneNumber, code;
    private Button send;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callback;
    private String verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // first thing and first activity to get everytghing started and start using firrebase
        FirebaseApp.initializeApp(this);

        useIsLogged();


        phoneNumber = findViewById(R.id.phoneNumber);
        code  = findViewById(R.id.code);
        send  = findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verifyCode == null)
                    startPhoneNumberVerification();
                else
                    verifyPhoneNumberWithCode();
            }
        });
        callback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                 send.setText("Verify Code");
                 verifyCode = s;
            }
        };
    }

    private void verifyPhoneNumberWithCode(){
        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verifyCode, code.getText().toString());
        signInWithPhoneAuthCredential(phoneAuthCredential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    useIsLogged();
                }
            }
        });
    }

    private void useIsLogged() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user  != null){
            startActivity(new Intent(getApplicationContext(), MainPageActivity.class));
        }
    }

    private void startPhoneNumberVerification() {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber.getText().toString(),
                60,
                TimeUnit.SECONDS,
                this,
                callback
        );
    }



}
