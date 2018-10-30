package chatapp.gohool.com.whatsupchatapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import chatapp.gohool.com.whatsupchatapp.R
import chatapp.gohool.com.whatsupchatapp.adapters.UsersAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_users.*


/**
 * A simple [Fragment] subclass.
 */
class ChatFragment : Fragment() {
    var mUsersDatabase: DatabaseReference? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var layout = inflater!!.inflate(R.layout.fragment_chat, container, false)

//        mUsersDatabase = FirebaseDatabase.getInstance().reference.child("Users")
//
//        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
//
//       // if (friendRecyclerViewId != null) {
//
//            friendRecyclerViewId.setHasFixedSize(true)
//
//
//            friendRecyclerViewId.adapter = UsersAdapter(mUsersDatabase!!, context)
//
//        friendRecyclerViewId.layoutManager = linearLayoutManager
//       // }else{
//          //  Log.d("Issues", "error")
//       // }
//



        return layout

    }


}// Required empty public constructor
