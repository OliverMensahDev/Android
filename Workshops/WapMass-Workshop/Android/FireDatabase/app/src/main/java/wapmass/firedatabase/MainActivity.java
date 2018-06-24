package wapmass.firedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get instance of database
        database = FirebaseDatabase.getInstance();
        //create a location to hold your data
        myRef = database.getReference("message");
        myRef.setValue("WapMAss Guys");

        //Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity.this, value, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
