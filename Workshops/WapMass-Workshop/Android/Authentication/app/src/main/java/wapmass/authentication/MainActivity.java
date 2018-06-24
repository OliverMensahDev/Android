package wapmass.authentication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, password;
    private Button login;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);

        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Register.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email.getText().toString(), password.getText().toString());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            if(currentUser.isEmailVerified()) {
                startActivity(new Intent(MainActivity.this, Dashboard.class));
            }else{
                Toast.makeText(MainActivity.this, "Please Make sure your email is verified ", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Get Authenticated by Registering or logining ", Toast.LENGTH_LONG).show();
        }
    }


    private void login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            if (currentUser != null) {
                                if(currentUser.isEmailVerified()) {
                                    startActivity(new Intent(MainActivity.this, Dashboard.class));
                                }else{
                                    Toast.makeText(MainActivity.this, "Please Make sure your email is verified ", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Get Authenticated by Registering or logining ", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
