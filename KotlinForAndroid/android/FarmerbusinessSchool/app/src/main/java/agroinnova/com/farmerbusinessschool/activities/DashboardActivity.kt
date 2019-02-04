package com.agroinnova.farmerbusinessschool.activities


import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.agroinnova.farmerbusinessschool.R
import com.agroinnova.farmerbusinessschool.fragments.*
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {

    private val accountFragment : AccountsFragment = AccountsFragment();
    private val educationHubFragment : EducationHubFragment = EducationHubFragment();
    private val calenderFragment : CalenderFragment = CalenderFragment();
    private val homeFragment : HomeFragment = HomeFragment();
    private val settingsFragment: SettingsFragment = SettingsFragment();
    private var transaction: FragmentTransaction? = null;

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->


        //        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//        transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
        transaction = supportFragmentManager.beginTransaction()
        transaction!!.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);

        when (item.itemId) {
            R.id.navigation_educationHub-> transaction!!.replace(R.id.fragment_container,  educationHubFragment);
            R.id.navigation_home -> transaction!!.replace(R.id.fragment_container, homeFragment);
            R.id.navigation_account-> transaction!!.replace(R.id.fragment_container,  accountFragment);
            R.id.navigation_calender -> transaction!!.replace(R.id.fragment_container, calenderFragment);
            R.id.navigation_more -> dialog()
        }
        transaction!!.commit()
        true

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if(null == savedInstanceState) {
            transaction = supportFragmentManager.beginTransaction()
            transaction!!.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);
            transaction!!.add(R.id.fragment_container, homeFragment)
            transaction!!.commit()
        }

    }

    private fun dialog(){
        val options = arrayOf("Cocoa Kudi", "Settings", "Sign Out")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Options")
        builder.setItems(options) { _, i ->
            //Set click event for each
            if (i == 0) {
                Toast.makeText(this@DashboardActivity
                    , "0", Toast.LENGTH_LONG).show()
            }
            if (i == 1){
                transaction = supportFragmentManager.beginTransaction()
                transaction!!.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);
                transaction!!.replace(R.id.fragment_container, settingsFragment);
                transaction!!.commit()
            }
            if (i == 2){
                startActivity(Intent(this@DashboardActivity, LoginActivity::class.java));
                finish();
            }
        }
        builder.show()
    }
}