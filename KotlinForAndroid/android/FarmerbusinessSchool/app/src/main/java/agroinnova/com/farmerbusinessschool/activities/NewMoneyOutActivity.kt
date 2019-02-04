package com.agroinnova.farmerbusinessschool.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.utils.UIutils

class NewMoneyOutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_money_out)
        UIutils.unFocus(this);
    }
}
