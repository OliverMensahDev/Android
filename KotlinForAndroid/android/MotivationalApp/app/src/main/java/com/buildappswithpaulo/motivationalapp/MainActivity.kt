package com.buildappswithpaulo.motivationalapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.buildappswithpaulo.motivationalapp.controller.QuoteData
import com.buildappswithpaulo.motivationalapp.controller.QuoteListAsycnResponse
import com.buildappswithpaulo.motivationalapp.controller.QuoteViewPagerAdapter
import com.buildappswithpaulo.motivationalapp.model.Quote
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var quoteViewPagerAdapter: QuoteViewPagerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        quoteViewPagerAdapter = QuoteViewPagerAdapter(supportFragmentManager, getFragments())
        viewPager.adapter = quoteViewPagerAdapter


    }


    private fun getFragments(): ArrayList<QuoteFragment> {

        // lateinit var fragmentList: ArrayList<QuoteFragment> // will not work
        val fragmentList = ArrayList<QuoteFragment>()
        QuoteData().getQuotes(object : QuoteListAsycnResponse {
            override fun processFinished(quotes: ArrayList<Quote>) {


                (0 until quotes.size).mapTo(fragmentList) {
                    QuoteFragment.newInstance(
                            quotes[it].quote,
                            quotes[it].author
                    )
                }
                quoteViewPagerAdapter.notifyDataSetChanged()


            }

        })

        return fragmentList

    }


}
