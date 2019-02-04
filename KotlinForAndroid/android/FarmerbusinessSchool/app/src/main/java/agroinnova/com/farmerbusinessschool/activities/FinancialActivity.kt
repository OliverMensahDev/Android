package com.agroinnova.farmerbusinessschool.activities

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentTransaction
import com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.fragments.MoneyInFragment
import com.agroinnova.farmerbusinessschool.fragments.MoneyOutFragment
import kotlinx.android.synthetic.main.activity_financial.*
class FinancialActivity : AppCompatActivity() {
    private var transaction: FragmentTransaction? = null;
    private val moneyInFragment = MoneyInFragment()
    private val moneyOutFragment = MoneyOutFragment()
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        transaction = supportFragmentManager.beginTransaction()
        transaction!!.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);

        when (item.itemId) {
            R.id.moneyout -> transaction!!.replace(R.id.fragment_container, moneyOutFragment);
            R.id.moneyin -> transaction!!.replace(R.id.fragment_container, moneyInFragment);
        }
        transaction!!.commit()
        true

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financial)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if(null == savedInstanceState) {
            transaction = supportFragmentManager.beginTransaction()
            transaction!!.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);
            transaction!!.add(R.id.fragment_container, moneyInFragment)
            transaction!!.commit()
        }
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolbar.title = getString(R.string.all_financial_records)
        toolbar.setTitleTextColor(Color.WHITE);
    }
}
