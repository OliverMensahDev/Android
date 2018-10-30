package chatapp.gohool.com.whatsupchatapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import chatapp.gohool.com.whatsupchatapp.R
import chatapp.gohool.com.whatsupchatapp.adapters.UsersAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_users.*


/**
 * A simple [Fragment] subclass.
 */
class UsersFragment : Fragment() {
    var mUsersDatabase: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var layout = inflater!!.inflate(R.layout.fragment_users, container, false)

        return layout
    }

    //This is what made it all work, by adding the code inside onViewCreated
    //Source: https://github.com/satorufujiwara/kotlin-android-flux/blob/master/mobile/src/main/kotlin/jp/satorufujiwara/kotlin/ui/main/drawer/MainDrawerFragment.kt

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        mUsersDatabase = FirebaseDatabase.getInstance().reference.child("Users")

        friendRecyclerViewId.setHasFixedSize(true)
        friendRecyclerViewId.adapter = UsersAdapter(mUsersDatabase!!, activity)

        friendRecyclerViewId.layoutManager = linearLayoutManager
        friendRecyclerViewId.adapter = UsersAdapter(mUsersDatabase!!, activity)





    }

    override fun onStart() {
        super.onStart()

    }


}// Required empty public constructor
