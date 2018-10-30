package introkotlinfirebase.gohool.com.introtokotlinfirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var mAuth: FirebaseAuth? = null
    private var currentUser: FirebaseUser? = null
    private var mAuthListener: FirebaseAuth.AuthStateListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseRef = firebaseDatabase.getReference("messages").push()


        createActID.setOnClickListener {

            //Create new User
            var email = emailID.text.toString().trim()
            var pwd = passwordID.text.toString().trim()

            mAuth!!.createUserWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener{
                        task: Task<AuthResult> ->
                        if (task.isSuccessful) {
                            var user:FirebaseUser = mAuth!!.currentUser!!
                            Log.d("User:", user.email.toString())

                        }else{
                            Log.d("Error:", task.exception.toString())

                        }

                    }

        }







        //Sign existing users in
        mAuth!!.signInWithEmailAndPassword("paulo@me.com", "password")
                .addOnCompleteListener{
                    task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        //Sign in was successful
                        Toast.makeText(this, "Signed In Successful",
                                Toast.LENGTH_LONG).show()
                    }else {
                        //Not successful
                        Toast.makeText(this, "Signed In Unsuccessful",
                                Toast.LENGTH_LONG).show()

                    }

                }




//        var employee = Employee("Bonni D", "Professor ",
//                "123 South Street", 25)
//
//        //databaseRef.setValue(employee)
//
//        //Read from our DB
//        databaseRef.addValueEventListener(object : ValueEventListener{
//
//            override fun onDataChange(dataSnapshot: DataSnapshot?) {
//                var value = dataSnapshot!!.value as HashMap<String, Any>
//               // var value = dataSnapshot!!.getValue(Employee::class.java)
//
//              //  Log.d("VALUE: ", value!!.get("name").toString())
//
////                for (name in value.entries) {
////                    Log.d("Names:", name.toString())
////                }
//
//
//            }
//
//            override fun onCancelled(error: DatabaseError?) {
//
//                Log.d("Error", error!!.message)
//
//            }
//        })


    }

    override fun onStart() {
        super.onStart()

        currentUser = mAuth!!.currentUser
        if (currentUser != null) {
             Toast.makeText(this, "User Is logged in", Toast.LENGTH_LONG).show()

        }else { Toast.makeText(this, "User Is logged out", Toast.LENGTH_LONG).show()}
        //call a function to update the userInterface with current user


    }











    class Employee() {
         var name: String? = null
         var position: String? = null
         var homeAddress: String? = null
         var age: Int? = null

        constructor(name: String, position: String, homeAddress: String, age: Int):this() {
            this.name = name
            this.position = position
            this.homeAddress = homeAddress
            this.age = age

        }
    }



}

