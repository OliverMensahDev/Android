package com.agroinnova.farmerbusinessschool.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.agroinnova.farmerbusinessschool.R
import com.agroinnova.farmerbusinessschool.data.DatabaseHelper
import com.agroinnova.farmerbusinessschool.models.MoneyInModel
import com.agroinnova.farmerbusinessschool.utils.UIutils
import kotlinx.android.synthetic.main.activity_new_money_in.*
import java.text.SimpleDateFormat
import java.util.*

class NewMoneyInActivity : AppCompatActivity() {
    private lateinit var  selectedDateAdded: String
    private lateinit var itemName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_money_in)
        UIutils.unFocus(this)
        populateMoneyInItems()
        getDateAdded()
        saveButton.setOnClickListener {
            val quantity = quantity.text.toString()
            val unitPrice = unit_price.text.toString()
            if(quantity  == "" &&  unitPrice == ""){
                Toast.makeText(this, "Complete the Form", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val newMoneyInModel = MoneyInModel()
            newMoneyInModel.dateAdded = selectedDateAdded
            newMoneyInModel.itemName = itemName
            val qty = quantity.toDouble()
            val unit = unitPrice.toDouble()
            newMoneyInModel.quantity = qty
            newMoneyInModel.totalPrice = qty * unit
            newMoneyInModel.unitPrice = unit

            if(DatabaseHelper.getInstance(this).createNewMoneyIn(newMoneyInModel, "0544892841")){
                Toast.makeText(this, "Successfully Added New Money In", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, FinancialActivity::class.java))
            }else{

            }

        }

    }

    private fun populateMoneyInItems(){
        val adapterCountries = ArrayAdapter(this, R.layout.spinner_item, DatabaseHelper.getInstance(this).getMoneyInItemList())
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_moneyin_items.adapter = adapterCountries;
        spinner_moneyin_items?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                itemName = parent!!.getItemAtPosition(position).toString()
            }
        }
    }


    private fun getDateAdded() {
        val myCalendar = Calendar.getInstance()
        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "dd/MM/yy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            dateAdded.setText(sdf.format(myCalendar.time))
            selectedDateAdded = dateAdded.text.toString()
        }
        dateAdded.setOnClickListener {
            DatePickerDialog(
                this@NewMoneyInActivity, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }
}
