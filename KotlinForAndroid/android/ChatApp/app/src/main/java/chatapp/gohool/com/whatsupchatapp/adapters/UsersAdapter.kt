package chatapp.gohool.com.whatsupchatapp.adapters

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import chatapp.gohool.com.whatsupchatapp.R
import chatapp.gohool.com.whatsupchatapp.activities.ChatActivity
import chatapp.gohool.com.whatsupchatapp.activities.ProfileActivity
import chatapp.gohool.com.whatsupchatapp.model.Users
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by paulodichone on 7/17/17.
 */
class UsersAdapter( databaseQuery: DatabaseReference, var context: Context): FirebaseRecyclerAdapter<Users, UsersAdapter.ViewHolder>(
        Users::class.java,
        R.layout.users_row,
        UsersAdapter.ViewHolder::class.java,
        databaseQuery

) {
    override fun populateViewHolder(viewHolder: UsersAdapter.ViewHolder?, user: Users?, position: Int) {

        var userId = getRef(position).key // users unique Id from firebase

        viewHolder!!.bindView(user!!, context)

        viewHolder.itemView.setOnClickListener {

            //Create an AlertDialog for options to see profile, or send message(chat)
            var options = arrayOf("Open Profile", "Send Message")

            var builder = AlertDialog.Builder(context)
            builder.setTitle("Select Options")
            builder.setItems(options, DialogInterface.OnClickListener { dialogInterface, i ->

                var userName = viewHolder!!.userNameTxt
                var userStat = viewHolder!!.userStatusText
                var profilePic = viewHolder!!.userProfilePicLink

                     //Set click event for each
                if (i == 0) {
                    //Open Profile


                    var profileIntent = Intent(context, ProfileActivity::class.java)
                    profileIntent.putExtra("userId", userId)

                    context.startActivity(profileIntent)
                }else {
                    //Send Message/ ChatActivity

                    var chatIntent = Intent(context, ChatActivity::class.java)
                    chatIntent.putExtra("userId", userId)
                    chatIntent.putExtra("name", userName)
                    chatIntent.putExtra("status", userStat)
                    chatIntent.putExtra("profile", profilePic)
                    context.startActivity(chatIntent)

                }
            })

            builder.show()





        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        //var context = context
        var userNameTxt: String? = null
        var userStatusText: String? = null
        var userProfilePicLink: String? = null

        fun bindView(user: Users, context: Context) {


            var userName = itemView.findViewById<TextView>(R.id.userName)
            var userStatus =  itemView.findViewById<TextView>(R.id.userStatus)
            var userProfilePic = itemView.findViewById<CircleImageView>(R.id.usersProfile)

            //set the Strings so we can pass in the intent
            userNameTxt = user.display_name
            userStatusText = user.user_status
            userProfilePicLink = user.thumb_image.toString()


            userName.text = user.display_name
            userStatus.text = user.user_status
            Log.d("IMAGE:", user.thumb_image.toString())
            Picasso.with(context).load(user.thumb_image).placeholder(R.drawable.profile_img).into(userProfilePic)



        }
    }
}