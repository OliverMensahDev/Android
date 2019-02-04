package com.agroinnova.farmerbusinessschool.holders

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.models.MoneyInModel
import kotlinx.android.synthetic.main.money_in_item.view.*

class MoneyInItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val name: TextView = itemView.name
    private val quantity: TextView = itemView.quantity
    private val unitPrice: TextView = itemView.unit_price
    private val totalPrice: TextView = itemView.total_price
    private val dateAdded: TextView = itemView.dateAdded
    val editButton: Button = itemView.editButton
    val deleteButton: Button = itemView.deleteButton
    private val image: TextView = itemView.image

    @SuppressLint("SetTextI18n")
    fun updateWithPage(currentPage: MoneyInModel){
        name.text = currentPage!!.itemName
        quantity.text = " Quantity: ${currentPage!!.quantity}"
        unitPrice.text = " Unit Price: ${currentPage!!.unitPrice} "
        totalPrice.text = " Total Price: ${currentPage!!.totalPrice}"
        dateAdded.text = "Date Added: ${currentPage!!.dateAdded}"
        image.text = currentPage!!.itemName!![0].toString()
    }
}