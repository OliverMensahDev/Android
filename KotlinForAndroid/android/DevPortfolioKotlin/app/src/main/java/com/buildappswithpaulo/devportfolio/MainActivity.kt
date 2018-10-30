package com.buildappswithpaulo.devportfolio

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.buildappswithpaulo.devportfolio.controller.DevPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = DevPagerAdapter(supportFragmentManager)
        tabLayoutId.setupWithViewPager(viewPager)

        tabLayoutId.setTabTextColors(Color.GRAY, Color.parseColor("white"))


    }
}
