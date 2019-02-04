package com.agroinnova.farmerbusinessschool.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox

import android.widget.LinearLayout
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.activities.LoginActivity
import com.agroinnova.farmerbusinessschool.utils.UIutils
import kotlinx.android.synthetic.main.fragment_add_diversified_crops.view.*

class AddDiversifiedCropsFragment : Fragment() {
    private var maizeLinearLayout: LinearLayout? = null;
    private var cassavaLinearLayout: LinearLayout? = null;
    private  var maizeCheckbox: CheckBox? = null;
    private  var cassavaCheckbox: CheckBox? = null;
    private var button:  Button? = null;
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_add_diversified_crops, container, false)
        UIutils.fadeIn(view.cardview)
        val toolbarTitle  = activity!!.findViewById(R.id.onboard) as TextView;
        toolbarTitle.text = "Other Crops Setup"
        maizeLinearLayout = view.llAddMaize;
        cassavaLinearLayout = view.llAddCassava
        maizeCheckbox = view.maizeCheckbox
        cassavaCheckbox = view.cassavaCheckbox
        button = view.setUpFarm;
        setVisibility(maizeLinearLayout!!, false)
        setVisibility(cassavaLinearLayout!!, false)

        eventListeners()
        return view
    }

    private fun setVisibility(layout: LinearLayout, visibilityType: Boolean?) {
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (visibilityType!!) {
                child.visibility = View.VISIBLE
            } else {
                child.visibility = View.GONE
            }
        }
    }

    private fun maizedChecked() {
        maizeCheckbox!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                setVisibility(maizeLinearLayout!!, true)
            } else {
                setVisibility(maizeLinearLayout!!, false)
            }
        }
    }
    private fun cassavaChecked() {
        cassavaCheckbox!!.setOnCheckedChangeListener { buttonView, isChecked ->
            if (buttonView.isChecked) {
                setVisibility(cassavaLinearLayout!!, true)
            } else {
                setVisibility(cassavaLinearLayout!!, false)
            }
        }
    }

    private fun eventListeners(){
        maizedChecked()
        cassavaChecked()
        button!!.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            activity!!.startActivity(intent)
            activity!!.finish()
        }
    }
}
