package chatapp.gohool.com.whatsupchatapp.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import chatapp.gohool.com.whatsupchatapp.R
import chatapp.gohool.com.whatsupchatapp.model.Messages
import chatapp.gohool.com.whatsupchatapp.model.Users
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference

/**
 * Created by paulodichone on 7/18/17.
 */
class ChatMessagesAdapter(databaseQuery: DatabaseReference, var context: Context): FirebaseRecyclerAdapter<Messages, ChatMessagesAdapter.ViewHolder>(
        Messages::class.java,
        R.layout.chat_user_row,
        ChatMessagesAdapter.ViewHolder::class.java,
        databaseQuery
){
    override fun populateViewHolder(viewHolder: ChatMessagesAdapter.ViewHolder?, messages: Messages?, position: Int) {

        var userId = getRef(position).key // users unique Id from firebase

        viewHolder!!.bindView(messages!!)

    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var message: String? = null
        var time: Long? = null


        fun bindView(messages: Messages) {
            var messageView = itemView.findViewById<TextView>(R.id.chatMessage)
            var timeView = itemView.findViewById<TextView>(R.id.chatTimeStamp)

            message = messageView.text.toString()
           // time = timeView.text.toString().toLong()


            messageView.text = messages.message
            //timeView.text = messages.time.toString()

//            messages.message = message
//            messages.time = time

            Log.d("STUFF!!", messages.message)
        }

    }

}