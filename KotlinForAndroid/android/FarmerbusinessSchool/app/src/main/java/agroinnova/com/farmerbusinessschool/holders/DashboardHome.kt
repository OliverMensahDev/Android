package com.agroinnova.farmerbusinessschool.holders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.models.HomeFragmentModel
import kotlinx.android.synthetic.main.dashboard_home_item.view.*

class DashboardHome(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.title
    private val first: CheckBox = itemView.first
    private val second: CheckBox = itemView.second

    private var currentPage: HomeFragmentModel? = null

    fun updateWithPage(item: HomeFragmentModel){
        currentPage = item
        title.text = currentPage!!.title
        first.text = currentPage!!.first
        second.text = currentPage!!.second
    }
}