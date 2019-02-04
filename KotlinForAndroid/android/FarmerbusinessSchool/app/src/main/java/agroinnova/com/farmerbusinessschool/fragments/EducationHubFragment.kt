package com.agroinnova.farmerbusinessschool.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.R;

class EducationHubFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater!!.inflate(R.layout.fragment_education_hub, container, false)
        val toolbarTitle  = getActivity()!!.findViewById(R.id.toolbar_title) as TextView;
        toolbarTitle.text = "Education  Hub"
        return view
    }
}
