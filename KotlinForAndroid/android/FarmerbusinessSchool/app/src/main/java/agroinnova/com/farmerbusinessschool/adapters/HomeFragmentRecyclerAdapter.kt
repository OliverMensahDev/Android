package com.agroinnova.farmerbusinessschool.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.agroinnova.farmerbusinessschool.R
import com.agroinnova.farmerbusinessschool.holders.DashboardHome
import com.agroinnova.farmerbusinessschool.models.HomeFragmentModel

class HomeFragmentRecyclerAdapter(currentResults: ArrayList<HomeFragmentModel>) : RecyclerView.Adapter<DashboardHome>() {
    private var currentResults: ArrayList<HomeFragmentModel> ?= null

    init{
        this.currentResults =   currentResults
    }

    override fun getItemCount(): Int {
        return currentResults!!.size
    }

    override fun onBindViewHolder(holder: DashboardHome, position: Int) {
        val page = currentResults!![position]
        holder.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardHome {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.dashboard_home_item, parent, false)
        return DashboardHome(item)
    }
}