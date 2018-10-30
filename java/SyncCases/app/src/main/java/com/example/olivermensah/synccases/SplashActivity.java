package com.example.olivermensah.synccases;



/**
 * Created by olivermensah on 12/3/17.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                if(currentUser != null){
                    Intent homeIntent = new Intent(SplashActivity.this, SignedInActivity.class);
                    finish();
                    startActivity(homeIntent);
                }else{
                    Intent homeIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    finish();
                    startActivity(homeIntent);
                }

            }
        }, 3000);
    }
    // [START on_start_check_user]
    @Override
    public void onStart() {
           super.onStart();
  }
  // [END on_start_check_user]

}
