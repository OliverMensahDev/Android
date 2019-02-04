package com.agroinnova.farmerbusinessschool.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import com.agroinnova.farmerbusinessschool.R;
import com.agroinnova.farmerbusinessschool.activities.NewMoneyOutActivity
import com.agroinnova.farmerbusinessschool.adapters.MoneyOutRecyclerAdapter
import com.agroinnova.farmerbusinessschool.data.DatabaseHandler
import com.agroinnova.farmerbusinessschool.models.MoneyOutItemModel
import com.agroinnova.farmerbusinessschool.utils.UIutils
import kotlinx.android.synthetic.main.fragment_money_out.view.*
import kotlinx.android.synthetic.main.money_out_item_popup.*
import kotlinx.android.synthetic.main.money_out_item_popup.view.*

class MoneyOutFragment : Fragment() {
    private var moneyOutRecycler: RecyclerView? = null
    private  var refresher: SwipeRefreshLayout? = null
    private  var adapter: MoneyOutRecyclerAdapter?= null;

    var data: ArrayList<MoneyOutItemModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_money_out, container, false)
        UIutils.fadeIn(view.container)
        setHasOptionsMenu(true)

        data.clear()
        data.addAll(DatabaseHandler.getMoneyOutData())
        adapter =  MoneyOutRecyclerAdapter(data, context!!)
        moneyOutRecycler = view.money_out_recycler
        refresher = view.refresher
        moneyOutRecycler!!.layoutManager = LinearLayoutManager(activity);
        moneyOutRecycler!!.adapter = adapter
        refresher?.setOnRefreshListener {
            //            newData()
        }
        return view
    }


    override  fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.meney_out, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_new_money_out -> context!!.startActivity(Intent(activity, NewMoneyOutActivity::class.java))
            R.id.add_new_money_out_item -> itemCreationPopUp()
        }
        return true
    }


    private fun itemCreationPopUp(){
        val dialogBuilder = AlertDialog.Builder(context!!)
        val view = layoutInflater.inflate(R.layout.money_out_item_popup, null)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        dialog.show()

        saveButton.setOnClickListener {

        }
    }
}
