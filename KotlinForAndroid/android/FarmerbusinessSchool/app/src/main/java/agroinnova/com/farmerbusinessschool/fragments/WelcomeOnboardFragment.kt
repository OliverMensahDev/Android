package com.agroinnova.farmerbusinessschool.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.utils.Constants
import com.agroinnova.farmerbusinessschool.utils.UIutils
import kotlinx.android.synthetic.main.fragment_welcome_onboard.view.*


class WelcomeOnboardFragment : Fragment(){
    private var editTextRegion: ViewGroup? = null;
    private  var editTextDistrict: LinearLayout? = null;

    private var spinnerRegion: ViewGroup? = null;
    private  var spinnerDistrict: LinearLayout? = null;
    private var diversifiedCropsFragment: DiversifiedCropsFragment = DiversifiedCropsFragment();

    private var transaction: FragmentTransaction? = null;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_welcome_onboard, container, false);
        val toolbarTitle  = activity!!.findViewById(R.id.onboard) as TextView;
        toolbarTitle.text = "Welcome! Register You Cocoa Farm"
        populateCountrySpinner(view)
        transaction  = activity!!.supportFragmentManager.beginTransaction();
        editTextRegion = view.llEditextRegion
        editTextDistrict = view.llEditextDistrict
        spinnerRegion = view.llSpinnerRegion
        spinnerDistrict = view.llSpinnerDistrict
        setVisibility(editTextRegion!!, false)
        setVisibility(editTextDistrict!!, false);


        view.addDefaultCrop.setOnClickListener {
            transaction!!.replace(R.id.fragment_container,  diversifiedCropsFragment);
            transaction!!.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);
            transaction!!.commit();
        }

        UIutils.unFocus(activity!!);
        return view
    }
    /**
     * Fill a Spinner with Countries;
     *
     *
     * */
    private fun populateCountrySpinner(view: View){
        val adapterCountries = ArrayAdapter<String>(context, R.layout.spinner_item, Constants.countries())
        adapterCountries.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.countries.adapter = adapterCountries;
        val myString = "Ghana" //the value you want the position for
        val myAdap = view.countries.adapter as ArrayAdapter<String> //cast to an ArrayAdapter
        val spinnerPosition = myAdap.getPosition(myString);
        //set the default according to value
        view.countries.setSelection(spinnerPosition);
        view.countries?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               if(parent!!.getItemAtPosition(position).toString() != myString){
                   setVisibility(editTextRegion!!, true)
                   setVisibility(editTextDistrict!!, true);

                   setVisibility(spinnerDistrict!!, false)
                   setVisibility(spinnerRegion!!, false);
               }
            }
        }
    }

    private fun setVisibility(layout: ViewGroup, visibilityType: Boolean?) {
        for (i in 0 until layout.childCount) {
            val child = layout.getChildAt(i)
            if (visibilityType!!) {
                child.visibility = View.VISIBLE
            } else {
                child.visibility = View.GONE
            }
        }
    }


}


