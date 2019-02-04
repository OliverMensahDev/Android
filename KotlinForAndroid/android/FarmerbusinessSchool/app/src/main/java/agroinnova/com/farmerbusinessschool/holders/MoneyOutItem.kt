package com.agroinnova.farmerbusinessschool.holders

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.models.MoneyOutItemModel
import kotlinx.android.synthetic.main.money_in_item.view.*

class MoneyOutItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.name
    private val quantity: TextView = itemView.quantity
    private val unitPrice: TextView = itemView.unit_price
    private val totalPrice: TextView = itemView.total_price
    private val dateAdded: TextView = itemView.dateAdded
    private val image: TextView = itemView.image
    val editButton: Button = itemView.editButton
    val deleteButton: Button = itemView.deleteButton
    private var currentPage: MoneyOutItemModel? = null

    @SuppressLint("SetTextI18n")
    fun updateWithPage(item: MoneyOutItemModel){
        currentPage = item
        name.text = currentPage!!.name
        quantity.text = " Quantity: ${currentPage!!.quantity}"
        unitPrice.text = " Unit Cost: ${currentPage!!.unitCost} "
        totalPrice.text = " Total Cost: ${currentPage!!.totalCost}"
        dateAdded.text = "Date Added: ${currentPage!!.dateAdded}"
        image.text = currentPage!!.name!![0].toString()


    }
}