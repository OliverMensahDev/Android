package com.agroinnova.farmerbusinessschool.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.activities.FinancialActivity
import com.agroinnova.farmerbusinessschool.data.DatabaseHandler
import com.agroinnova.farmerbusinessschool.utils.UIutils
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.fragment_account.view.*
import java.util.ArrayList
import com.github.mikephil.charting.components.Legend



class AccountsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val moneyIn = DatabaseHandler.getTotalMoneyIn();
        val moneyOut = DatabaseHandler.getTotalMoneyOut()
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_account, container, false)
        UIutils.fadeIn(view.financialLayout)
        val toolbarTitle  = activity!!.findViewById(R.id.toolbar_title) as TextView;
        toolbarTitle.text = getString(R.string.title_financial_account)
        view.moneyin.text = "GHS ${moneyIn}"
        view.moneyout.text = "GHS ${moneyOut}"
        val netProfit = moneyIn - moneyOut
        if(netProfit < 0) {
            view.netProfitCard.setBackgroundColor(Color.RED)
            view.netProfit.setTextColor(Color.WHITE);
            view.netProfitDesc.text = getString(R.string.net_loss)
        }
        view.netProfit.text = "GHS ${netProfit}"
        setupGraph(view, moneyIn, moneyOut)
        clickListen(view)
        return view
    }

    private fun setupGraph(view: View,moneyIn: Float, moneyOut: Float) {
        //populating list of pieentries
        val pieEntries = ArrayList<PieEntry>()
        if(moneyIn > 0) pieEntries.add(PieEntry(moneyIn, "Money In"))
        if(moneyOut > 0) pieEntries.add(PieEntry(moneyOut, "Money Out"))

        //pie dataset
        val pieDataSet = PieDataSet(pieEntries, "")

        val myList = arrayListOf<Int>()
        myList.addAll(ColorTemplate.COLORFUL_COLORS.toList())
        pieDataSet.colors = myList
        val data = PieData(pieDataSet)
        //Get Chart
        val chart = view.dashboard_chart
        data.setValueTextSize(25.0f)
        data.setValueTextColor(Color.WHITE)
        chart.data = data
        chart.description.isEnabled = false
        val legend = chart.legend
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.textSize = 18.0f
        legend.setDrawInside(false)
        chart.invalidate()
    }

    fun clickListen(view: View){
        view.imageicon.setOnClickListener {
            context!!.startActivity(Intent(context, FinancialActivity::class.java));
        }
    }
}
