package chatapp.gohool.com.whatsupchatapp.activities

import android.app.ProgressDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import chatapp.gohool.com.whatsupchatapp.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        loginButtonId.setOnClickListener {
            var email = loginEmailEt.text.toString().trim()
            var password = loginPasswordEt.text.toString().trim()

            if (!TextUtils.isEmpty(email)
                    || !TextUtils.isEmpty(password)){

                loginUser(email, password)
            }

        }
    }

    private fun  loginUser(email: String, password: String) {
        mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{
                    task: Task<AuthResult> ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()

                    }else{
                        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG)
                                .show()

                    }
                }
    }


}
