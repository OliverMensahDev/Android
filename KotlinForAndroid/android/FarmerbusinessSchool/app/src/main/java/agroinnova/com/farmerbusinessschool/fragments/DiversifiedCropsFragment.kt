package com.agroinnova.farmerbusinessschool.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton;
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.R
import com.agroinnova.farmerbusinessschool.activities.LoginActivity
import kotlinx.android.synthetic.main.fragment_diversified_crop.view.*

class DiversifiedCropsFragment : Fragment() {
    private var  dialog: AlertDialog? = null;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_diversified_crop, container, false)
        getSelectedRadioButton(view);
        val toolbarTitle  = activity!!.findViewById(R.id.onboard) as TextView;
        toolbarTitle.text = "Other Crops!!"
        return view
    }

    private fun getSelectedRadioButton(view: View){
        view.hasOtherCommercialCrops.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = view.findViewById(checkedId)
            if(radio.text == getString(R.string.yes)){
                createAlert("Additional Crop", "Awesome, you have other crops grown for commercial purpose!",  "Proceed To Add Crops" , "", object : OnOptionSelect {
                    override fun onSelectYes() {
                        dialog!!.closeOptionsMenu();
                        val addDiversifiedCropsFragment: AddDiversifiedCropsFragment  = AddDiversifiedCropsFragment();
                        val transaction  = activity!!.supportFragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment_container,  addDiversifiedCropsFragment);
                        transaction.setCustomAnimations(R.anim.enter_anim, R.anim.exit_anim);
                        transaction.commit();
                    }
                })
            }else{
                createAlert("No Additional Crops", "Awesome, we hope you will have the strength to grow more crops!", "Close" , "", object : OnOptionSelect {
                    override fun onSelectYes() {
                        dialog!!.closeOptionsMenu();
                        val intent = Intent(context, LoginActivity::class.java)
                        activity!!.startActivity(intent)
                        activity!!.finish()
                    }
                })
            }
        }
    }
    private fun createAlert(title: String, message: String, yes: String, no:String , select: OnOptionSelect){
        val builder = AlertDialog.Builder(context!!)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(yes) { _, _ ->
          select.onSelectYes()
        }
        builder.setNegativeButton(no){ _, _ ->
        }
        dialog = builder.create()
        dialog!!.show()
    }

    interface OnOptionSelect {
        fun onSelectYes()
    }
}
