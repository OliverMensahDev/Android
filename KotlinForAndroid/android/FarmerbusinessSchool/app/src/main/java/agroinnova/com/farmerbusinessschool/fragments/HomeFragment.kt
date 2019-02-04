package com.agroinnova.farmerbusinessschool.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.adapters.HomeFragmentRecyclerAdapter
import com.agroinnova.farmerbusinessschool.data.DatabaseHandler
import com.agroinnova.farmerbusinessschool.holders.DashboardHome
import com.agroinnova.farmerbusinessschool.models.HomeFragmentModel
import com.agroinnova.farmerbusinessschool.utils.UIutils
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {
    private var homeRecycler: RecyclerView? = null
    private  var refresher: SwipeRefreshLayout? = null
    private  var adapter: HomeFragmentRecyclerAdapter? = null;

    var data: ArrayList<HomeFragmentModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_home, container, false)
        val toolbarTitle  = activity!!.findViewById(R.id.toolbar_title) as TextView;
        toolbarTitle.text = getString(R.string.title_home)
        UIutils.fadeIn(view.homeLayout)
        data.clear()
        data.addAll(DatabaseHandler.getdata())
        adapter =  HomeFragmentRecyclerAdapter(data)
        homeRecycler = view.home_recycler
        refresher = view.refresher

        homeRecycler!!.layoutManager = LinearLayoutManager(activity);
        homeRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
            newData()
        }




        return view
    }

    fun newData(){
        refresher?.isRefreshing = true
        data.clear()
        data.addAll(DatabaseHandler.update())
        adapter!!.notifyDataSetChanged()
        refresher?.isRefreshing = false
    }
}
