package com.buildappswithpaulo.motivationalapp.controller

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.buildappswithpaulo.motivationalapp.QuoteFragment

/**
 * Created by paulodichone on 2/9/18.
 */
class QuoteViewPagerAdapter(fm: FragmentManager, fragments: ArrayList<QuoteFragment>)
    : FragmentPagerAdapter(fm) {


     var framengList: ArrayList<QuoteFragment> = fragments

    override fun getItem(position: Int): Fragment {
        return framengList[position]

    }

    override fun getCount(): Int {
        return framengList.size
    }
}