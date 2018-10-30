package bawo.studentnewsdigest.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


import bawo.studentnewsdigest.model.Article;

public class FirebaseUtil {
    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference databaseReference;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseStorage firebaseStorage;


    public static DatabaseReference setupDatabase(String ref){
        if(firebaseDatabase == null){
            firebaseDatabase = FirebaseDatabase.getInstance();
        }
        databaseReference = firebaseDatabase.getReference(ref);
        return databaseReference;
    }

    public static FirebaseAuth setupAuth(){
        if(firebaseAuth ==null){
            firebaseAuth = FirebaseAuth.getInstance();
        }
        return firebaseAuth;
    }

    public static StorageReference setStorage(String ref){
        if(firebaseStorage == null){
            firebaseStorage = FirebaseStorage.getInstance();
        }
        StorageReference storageReference = firebaseStorage.getReference(ref);
        return storageReference;
    }
}
