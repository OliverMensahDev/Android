package e.olivermensah.fireauthdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import e.olivermensah.fireauthdb.Model.User;

public class Dashboard extends AppCompatActivity {
    private TextView emailPlaceHolder, namePlaceHolder, agePlaceHolder, phone;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;
    private FirebaseAuth mAuth;
    private Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        emailPlaceHolder = findViewById(R.id.EmailPlaceHolder_DA);
        namePlaceHolder = findViewById(R.id.NamePlaceHolder_DA);
        agePlaceHolder = findViewById(R.id.AgePlaceHolder_DA);
        phone = findViewById(R.id.PhonePlaceHolder_DA);
        logout = findViewById(R.id.logout);

        mAuth = FirebaseAuth.getInstance();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(Dashboard.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        readUserData();
    }

    public void readUserData(){
        Query query = FirebaseDatabase.getInstance().getReference().child("users")
                .orderByKey()
                .equalTo(FirebaseAuth.getInstance().getCurrentUser().getUid());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //this loop will return a single result
                for(DataSnapshot singleSnapshot: dataSnapshot.getChildren()){
                    User user = singleSnapshot.getValue(User.class);
                    namePlaceHolder.setText(user.getName());
                    emailPlaceHolder.setText(user.getEmail());
                    phone.setText(user.getPhone());
                    agePlaceHolder.setText(user.getAge());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }




}
