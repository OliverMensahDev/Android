package com.agroinnova.farmerbusinessschool.data

import com.agroinnova.farmerbusinessschool.models.HomeFragmentModel
import com.agroinnova.farmerbusinessschool.models.MoneyInItemModel1
import com.agroinnova.farmerbusinessschool.models.MoneyOutItemModel
import java.text.SimpleDateFormat
import java.util.*

object DatabaseHandler{
    fun getdata(): ArrayList<HomeFragmentModel>{
        val data : ArrayList<HomeFragmentModel> = ArrayList()
        val one = HomeFragmentModel();
        one.title = "Education Hub"
        one.first = "Best Practices on Cocoa"
        one.second = "Disease Attach"
        data.add(one)
        val two = HomeFragmentModel();
        two.title = "Cropping Calender"
        two.first = "Date(13th July) - Pruning Seedlings"
        two.second = "Date(24th December) - Spraying Seedlings"
        data.add(two)
        val three = HomeFragmentModel();
        three.title = "Account Summary"
        three.first = "Money Out:  No recent expenses"
        three.second = "Money In: GHS 200.00"
        data.add(three)
        return data;
    }

    fun update(): ArrayList<HomeFragmentModel>{
        val data : ArrayList<HomeFragmentModel> = ArrayList()
        val one = HomeFragmentModel();
        one.title = "Education Hub"
        one.first = "Weeding Farm"
        one.second = "Drying Beans"
        data.add(one)
        val two = HomeFragmentModel();
        two.title = "Cropping Calender"
        two.first = "Date(1st January) - Weeding Farm"
        two.second = "Date(4th Match) - Spraying Farm"
        data.add(two)
        val three = HomeFragmentModel();
        three.title = "Account Summary"
        three.first = "Money Out:  GHS 200"
        three.second = "Money In: No Recent Income"
        data.add(three)
        return data;
    }
    fun getTotalMoneyOut(): Float {
        return 30.0f
    }
    fun getTotalMoneyIn(): Float {
        return 42.0f
    }

    fun getMoneyInData(): ArrayList<MoneyInItemModel1>{
        val data: ArrayList<MoneyInItemModel1> = ArrayList()
        val one = MoneyInItemModel1()
        one.name = "Cocoa Proceeds"
        one.quantity= 60.00
        one.unitPrice = 500.00
        one.totalPrice =  one.quantity!! * one.quantity!!
        one.dateAdded = getDate()
        data.add(one)

        val two = MoneyInItemModel1()
        two.name = "Pawpaw Proceeds"
        two.quantity= 60.00
        two.unitPrice = 500.00
        two.totalPrice =  two.quantity!! * two.quantity!!
        two.dateAdded = getDate()
        data.add(two)
        return data
    }

    fun getMoneyOutData(): ArrayList<MoneyOutItemModel>{
        val data: ArrayList<MoneyOutItemModel> = ArrayList()
        val one = MoneyOutItemModel()
        one.name = "Cocoa Seedlings"
        one.quantity= 60.00
        one.unitCost = 500.00
        one.totalCost =  one.quantity!! * one.quantity!!
        one.dateAdded = getDate()
        data.add(one)

        val two = MoneyOutItemModel()
        two.name = "Workers Salary"
        two.quantity= 60.00
        two.unitCost = 500.00
        two.totalCost =  two.quantity!! * two.quantity!!
        two.dateAdded = getDate()
        data.add(two)
        return data
    }

    fun getDate(): String{
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(Date())
    }
}