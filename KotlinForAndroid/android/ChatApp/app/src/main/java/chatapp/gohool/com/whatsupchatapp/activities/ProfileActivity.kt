package chatapp.gohool.com.whatsupchatapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import chatapp.gohool.com.whatsupchatapp.R

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        supportActionBar!!.title = "Profile"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }
}
