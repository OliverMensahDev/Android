package com.agroinnova.farmerbusinessschool.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.fragments.WelcomeOnboardFragment


class OnboardingActivity : AppCompatActivity()  {

    private val welcomeOnboardFragment : WelcomeOnboardFragment = WelcomeOnboardFragment();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        if(null == savedInstanceState) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, welcomeOnboardFragment)
            transaction.commit();
        }

    }
}
