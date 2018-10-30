package chatapp.gohool.com.whatsupchatapp.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import chatapp.gohool.com.whatsupchatapp.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.progress_bar.*

class CreateAccountActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        mAuth = FirebaseAuth.getInstance()


        accountCreateActBtn.setOnClickListener {

            var email = accountEmailEt.text.toString().trim()
            var password = accountPasswordEt.text.toString().trim()
            var displayName = accountDisplayNameEt.text.toString().trim()
            if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)
                    || !TextUtils.isEmpty(displayName)) {

                createAccount(email,password,displayName)

            } else {
                Toast.makeText(this, "Please fill out all fields",
                        Toast.LENGTH_LONG).show()
            }

        }


    }

    fun createAccount(email: String, password: String, displayName: String) {


        mAuth!!.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        //we now save the displayName to db
                        var currentUser = mAuth!!.currentUser!!
                        var userId = currentUser.uid

                        mDatabase = FirebaseDatabase.getInstance().reference.child("Users")
                                .child(userId)

                        //Create a Hashmap to construct our User object
                        var userObject = HashMap<String, String>()
                        userObject.put("display_name", displayName)
                        userObject.put("status", "Hello there, friends!")
                        userObject.put("image", "default")
                        userObject.put("thumb_image", "default")

                        mDatabase?.setValue(userObject)!!.addOnCompleteListener {
                            task: Task<Void> ->
                              if (task.isSuccessful){

                                  //Go to dashboard
                                  startActivity(Intent(this, DashboardActivity::class.java))
                                  finish()

                              }else {

                                  Log.d("Error", task.exception.toString())
                              }
                        }


                    }else{
                        Log.d("Error:", task.exception.toString())
                    }
                }


    }
}
