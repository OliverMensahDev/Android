package com.example.olivermensah.googlesignin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SignOutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(getIntent().getStringExtra("name"));


        TextView email = (TextView) findViewById(R.id.email);
        email.setText(getIntent().getStringExtra("email"));

        TextView phone = (TextView) findViewById(R.id.phone);
        phone.setText(getIntent().getStringExtra("phone"));



        ImageView signOut = (ImageView) findViewById(R.id.singout);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent homeIntent = new Intent(SignOutActivity.this, SignInActivity.class);
                finish();
                startActivity(homeIntent);
            }
        });

    }

}
