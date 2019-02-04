package com.agroinnova.farmerbusinessschool.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.agroinnova.farmerbusinessschool.R
import com.agroinnova.farmerbusinessschool.holders.MoneyInItem
import com.agroinnova.farmerbusinessschool.models.MoneyInModel


class MoneyInRecyclerAdapter(currentResults: ArrayList<MoneyInModel>, context: Context) : RecyclerView.Adapter<MoneyInItem>() {
    private var currentResults: ArrayList<MoneyInModel> ?= null
    private var context: Context? = null

    init{
        this.currentResults =   currentResults
        this.context = context
    }
    override fun getItemCount(): Int {
        return currentResults!!.size
    }

    override fun onBindViewHolder(holder: MoneyInItem, position: Int) {
        val page = currentResults!![position]
        holder.updateWithPage(page)
        holder.editButton.setOnClickListener {
            Toast.makeText(context, "Edit", Toast.LENGTH_LONG).show()
        }

        holder.deleteButton.setOnClickListener {
            Toast.makeText(context, "Delete", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyInItem {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.money_in_item, parent, false)
        return MoneyInItem(item)
    }
}