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
import com.agroinnova.farmerbusinessschool.activities.NewMoneyInActivity
import com.agroinnova.farmerbusinessschool.adapters.MoneyInRecyclerAdapter
import com.agroinnova.farmerbusinessschool.utils.UIutils
import kotlinx.android.synthetic.main.fragment_money_in.view.*
import android.view.MenuInflater
import android.widget.Toast
import com.agroinnova.farmerbusinessschool.data.DatabaseHelper
import com.agroinnova.farmerbusinessschool.models.MoneyInItemModel
import com.agroinnova.farmerbusinessschool.models.MoneyInModel
import kotlinx.android.synthetic.main.money_in_item_popup.view.*


class MoneyInFragment : Fragment(){
    private var moneyInRecycler: RecyclerView? = null
    private  var refresher: SwipeRefreshLayout? = null
    private  var adapter: MoneyInRecyclerAdapter? = null;

    var data: ArrayList<MoneyInModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_money_in, container, false)
        UIutils.fadeIn(view.container)
        setHasOptionsMenu(true);
        data.clear()
        data.addAll(DatabaseHelper.getInstance(context!!).getMoneyIns())
        adapter =  MoneyInRecyclerAdapter(data, context!!)
        moneyInRecycler = view.money_in_recycler
        refresher = view.refresher
        moneyInRecycler!!.layoutManager = LinearLayoutManager(activity);
        moneyInRecycler!!.adapter = adapter

        refresher?.setOnRefreshListener {
//            newData()
        }
        return view
    }

    override  fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.meney_in, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override  fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.add_new_money_in -> context!!.startActivity(Intent(activity, NewMoneyInActivity::class.java))
            R.id.add_new_money_in_item -> itemCreationPopUp()
        }
        return true
    }

    private fun itemCreationPopUp(){
        val dialogBuilder = AlertDialog.Builder(context!!)
        val view = layoutInflater.inflate(R.layout.money_in_item_popup, null)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        dialog.show()

        view.saveButton.setOnClickListener {
            val name: String = view.inputEditTextItemName.text.toString().trim()
            if (UIutils.validateInput(name, view.inputLayoutItemName, "Enter a Item Name")){
                val moneyInItem = MoneyInItemModel()
                moneyInItem.name = name
                if(DatabaseHelper.getInstance(context!!.applicationContext).createMoneyInItem(moneyInItem)){
                    Toast.makeText(context, "Item Successfully Added", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                }else{
                    Toast.makeText(context, "Something Bad Happened: Item Not Saved", Toast.LENGTH_SHORT).show()
                }

            }
        }
    }
}
