package com.agroinnova.farmerbusinessschool.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.agroinnova.farmerbusinessschool.R
import com.agroinnova.farmerbusinessschool.holders.MoneyOutItem
import com.agroinnova.farmerbusinessschool.models.MoneyOutItemModel


class MoneyOutRecyclerAdapter(currentResults: ArrayList<MoneyOutItemModel>, context: Context) : RecyclerView.Adapter<MoneyOutItem>() {
    private var currentResults: ArrayList<MoneyOutItemModel> ?= null
    private var context: Context? = null;

    init{
        this.currentResults =   currentResults
        this.context = context
    }

    override fun getItemCount(): Int {
        return currentResults!!.size
    }

    override fun onBindViewHolder(holder: MoneyOutItem, position: Int) {
        val page = currentResults!![position]
        holder.updateWithPage(page)
        holder.deleteButton.setOnClickListener {
            Toast.makeText(context, "Delete Money Out", Toast.LENGTH_LONG).show()
        }
        holder.editButton.setOnClickListener {
            Toast.makeText(context, "Edit Money Out", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyOutItem {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.money_in_item, parent, false)
        return MoneyOutItem(item)
    }
}