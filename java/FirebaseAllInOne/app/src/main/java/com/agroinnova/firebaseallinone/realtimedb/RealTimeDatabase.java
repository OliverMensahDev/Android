package com.agroinnova.firebaseallinone.realtimedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agroinnova.firebaseallinone.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RealTimeDatabase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("message");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_database);

        // Read from the database
        final TextView dataTextView =  findViewById(R.id.textView);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
// This method is called once with the initial value and again
// whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                dataTextView.setText(value);
            }
            @Override
            public void onCancelled(DatabaseError error) {
// Failed to read value
                dataTextView.setText("Error " + error.toString());
            }
        });

        Button dbButton =  findViewById(R.id.button2);
        dbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// Write a message to the database
                myRef.setValue("Hello, World!");
            }
        });
    }

}
