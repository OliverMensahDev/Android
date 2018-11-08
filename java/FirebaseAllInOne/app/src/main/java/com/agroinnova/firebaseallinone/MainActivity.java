package com.agroinnova.firebaseallinone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agroinnova.firebaseallinone.auth.Auth;
import com.agroinnova.firebaseallinone.realtimedb.RealTimeDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Button real =  findViewById(R.id.realtimeDB);
        real.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RealTimeDatabase.class));
            }
        });

        Button auth =  findViewById(R.id.auth);
        auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Auth.class));
            }
        });
    }
}
