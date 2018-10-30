package e.olivermensah.fireauthdb;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import e.olivermensah.fireauthdb.Model.User;

public class RegistrationActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Button reg;
    private EditText email, pass, age, fname, lastName, phone;
    private ImageButton selectProfile;
    private int GALLERY_CODE = 1;
    private Uri imageUri, downloadedURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mAuth = FirebaseAuth.getInstance();
        reg = findViewById(R.id.regRA);

        email = findViewById(R.id.emailRA);
        pass = findViewById(R.id.passwordRA);

        age = findViewById(R.id.ageRA);
        fname = findViewById(R.id.firstNameRA);
        lastName = findViewById(R.id.lastNameRA);
        phone = findViewById(R.id.phoneNumberRA);
        selectProfile = findViewById(R.id.profile);

        selectProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/**");
                startActivityForResult(galleryIntent,GALLERY_CODE) ;
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser(email.getText().toString(), pass.getText().toString());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            selectProfile.setImageURI(imageUri);
        }
    }

    private void createUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendVerificationEmail();
                                uploadAndSave();
                        } else {
                            Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
    /**
     * sends an email verification link to the user
     */
    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Sent Verification Email", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegistrationActivity.this, "Couldn't Verification Send Email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
            Toast.makeText(RegistrationActivity.this, "Null", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadAndSave(){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference().child("MyProfile").child(imageUri.getLastPathSegment());
        storageReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                downloadedURL = taskSnapshot.getDownloadUrl();
                User user = new User();
                user.setEmail(email.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setAge(age.getText().toString());
                user.setName(fname.getText().toString() + " "+ lastName.getText().toString());
                user.setProfile(downloadedURL.toString());
                saveUserData(user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        });

    }

    public void saveUserData(User user){
        FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            mAuth.signOut();
                            Toast.makeText(RegistrationActivity.this, "save", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(RegistrationActivity.this, "not saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
