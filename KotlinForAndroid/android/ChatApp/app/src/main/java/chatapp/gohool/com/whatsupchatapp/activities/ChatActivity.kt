package chatapp.gohool.com.whatsupchatapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import chatapp.gohool.com.whatsupchatapp.R
import chatapp.gohool.com.whatsupchatapp.adapters.ChatMessagesAdapter
import chatapp.gohool.com.whatsupchatapp.adapters.UsersAdapter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.chat_user_row.*
import kotlinx.android.synthetic.main.custom_bar_image.view.*
import kotlinx.android.synthetic.main.fragment_users.*

class ChatActivity : AppCompatActivity() {
    var userId: String? = null
    var mRootRef: DatabaseReference? = null
    var mAuth: FirebaseAuth? = null
    var mCurrentUserId: String? = null
    var chatAdapter: ChatMessagesAdapter? = null
    private var mLinearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mAuth = FirebaseAuth.getInstance()

        mRootRef = FirebaseDatabase.getInstance().reference
        mCurrentUserId = mAuth!!.currentUser!!.uid

        mLinearLayoutManager =  LinearLayoutManager(this)
        mLinearLayoutManager!!.stackFromEnd = true



        //Setup custom bar image
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        if (intent.extras != null) {
            userId = intent.extras.getString("userId")

            var profileImageLink = intent.extras.get("profile").toString()
            supportActionBar!!.title = intent.extras.get("name").toString()

            //Create a LayoutInflater to get the custom bar image
            var inflater = this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE)
                            as LayoutInflater

            var actionBarView = inflater.inflate(R.layout.custom_bar_image, null)
            actionBarView.customBarName.text = intent.extras.get("name").toString()
            Picasso.with(this).load(profileImageLink).into(actionBarView.CustomBarCircleImageView)
            supportActionBar!!.customView = actionBarView


            mRootRef!!.child("Users").child(userId).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot?) {
                    //This is where we get the last time seen and anything else we need!
                }

                override fun onCancelled(databaseError: DatabaseError?) {

                }
            })


            mRootRef!!.child("Chat").child(mCurrentUserId).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot?) {
                    if (!dataSnapshot!!.hasChild(userId)) {
                        //Create one then
                        var chatAddMap = HashMap<String, Any>()
                        chatAddMap.put("seen", false)
                        chatAddMap.put("timestamp", ServerValue.TIMESTAMP)


                        //ChatUser Map -- We are adding chat messages in both users objects
                        var chatUserMap = HashMap<String, Any>()
                        chatUserMap.put("Chat/ ${mCurrentUserId} / $userId", chatAddMap)
                        chatUserMap.put("Chat/ ${userId} / $mCurrentUserId", chatAddMap)

                        mRootRef!!.updateChildren(chatUserMap, DatabaseReference.CompletionListener {
                            databaseError, databaseReference ->
                            if (databaseError != null) {
                                //Error!
                                Toast.makeText(this@ChatActivity, databaseError.message.toString(),
                                        Toast.LENGTH_LONG).show()
                               // Log.d("Error", databaseError.message.toString())
                            }else {

                                //Populate the recyclerview if there's been previous conversations



                            }


                          })

                        }else { }

                    }

                override fun onCancelled(p0: DatabaseError?) {

                }

                })



            //Get all messages and populate into the RecyclerView
            //Setup RecyclerView
            var linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
            chatRecyclerViewId.setHasFixedSize(true)
            chatRecyclerViewId.layoutManager = linearLayoutManager


            var messageDBData = mRootRef!!.child("Messages").child(userId).child(mCurrentUserId)


            chatAdapter = ChatMessagesAdapter(messageDBData , applicationContext)


              mRootRef!!.child("Messages").child(userId).child(mCurrentUserId)
                    .addChildEventListener(object : ChildEventListener{
                        override fun onChildAdded(dataSnapshot: DataSnapshot?, p1: String?) {
                            if (dataSnapshot != null) {


                                //mRootRef!!.child("Messages").child(mCurrentUserId).child(userId)




                                chatAdapter!!.notifyDataSetChanged()
                                //linearLayoutManager.scrollToPosition(dataSnapshot.childrenCount.toInt() - 1)

                                Log.d("Data ==>", dataSnapshot.child("message").toString())

                            }

                        }

                        override fun onChildChanged(dataSnapshot: DataSnapshot?, p1: String?) {
                            Log.d("Data ==>", dataSnapshot!!.child("message").toString())

                        }

                        override fun onChildMoved(p0: DataSnapshot?, p1: String?) {

                        }

                        override fun onChildRemoved(dataSnapshot: DataSnapshot?) {

                        }

                        override fun onCancelled(p0: DatabaseError?) {

                        }
                    })

            chatAdapter!!.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    var messageCount = chatAdapter!!.itemCount
                    var lastVisiblePosition = mLinearLayoutManager!!.findLastCompletelyVisibleItemPosition()
                   // Log.d("CCOUNT!!", message.toString())
                    //var lastVisiblePos = mLinearLayoutManager!!.findLastCompletelyVisibleItemPosition()

                    if (lastVisiblePosition == -1
                            || (positionStart >= (messageCount - 1)
                            && lastVisiblePosition == (positionStart - 1))) {


                        chatRecyclerViewId.scrollToPosition(positionStart)
                    }
                }
            })
            chatRecyclerViewId.setHasFixedSize(true)
            chatRecyclerViewId.layoutManager = linearLayoutManager
            chatRecyclerViewId.adapter = chatAdapter


            }//End if Intent


        //Send Chat messages
        chatSendButton.setOnClickListener {

            sendMessage()

        }



    }

    private fun sendMessage() {
        var message = chatEditText.text.toString().trim()
        if (!TextUtils.isEmpty(message)) {
            //Add message to database
            var currentUserRef = "Messages/$mCurrentUserId / $userId/"
            var chatUserRef = "Messages/$userId/$mCurrentUserId/"

            var userMessagePushDB = mRootRef!!.child("Messages").child(mCurrentUserId)
                    .child(userId).push()

            var pushId = userMessagePushDB!!.key

            var messageMap = HashMap<String, Any>()
            messageMap.put("message", message)
            messageMap.put("send", false)
            messageMap.put("type", "text")
            messageMap.put("time", ServerValue.TIMESTAMP)

            //MessageUserMap
            var messageUserMap = HashMap<String, Any>()
            messageUserMap.put(currentUserRef + "/" + pushId, messageMap)
            messageUserMap.put(chatUserRef + "/" + pushId, messageMap)


            chatEditText.setText("")

            //mRootRef!!.updateChildren(messageUserMap).addOnCompleteListener

            mRootRef!!.updateChildren(messageUserMap).addOnCompleteListener {
                task: Task<Void> ->
                if (task.isSuccessful) {

//                    mRootRef!!.child("Messages").child(userId).child(mCurrentUserId)
//                            .addValueEventListener(object : ValueEventListener{
//
//                                override fun onCancelled(p0: DatabaseError?) {
//
//
//                                }
//
//                                override fun onDataChange(p0: DataSnapshot?) {
////
//                                }
//                            })

                    chatAdapter!!.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
                        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                            super.onItemRangeInserted(positionStart, itemCount)
                            var messageCount = chatAdapter!!.itemCount
                            var lastVisiblePosition = mLinearLayoutManager!!.findLastCompletelyVisibleItemPosition()
                            Log.d("CCOUNT!!", message.toString())
                            //var lastVisiblePos = mLinearLayoutManager!!.findLastCompletelyVisibleItemPosition()

                            if (lastVisiblePosition == -1
                                    || (positionStart >= (messageCount - 1)
                                    && lastVisiblePosition == (positionStart - 1))) {


                                chatRecyclerViewId.scrollToPosition(positionStart)
                            }
                        }
                    })

                    chatRecyclerViewId.layoutManager = mLinearLayoutManager
                    chatRecyclerViewId.adapter = chatAdapter
                    chatAdapter!!.notifyDataSetChanged()


                }else{


                }
            }

        }

    }

    override fun onStart() {
        super.onStart()
    }

}








/**
 *
 *   Default code:
 *
 *
 *
 */

/*

  package chatapp.gohool.com.whatsupchatapp.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import chatapp.gohool.com.whatsupchatapp.R
import chatapp.gohool.com.whatsupchatapp.adapters.ChatMessagesAdapter
import chatapp.gohool.com.whatsupchatapp.adapters.UsersAdapter
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.custom_bar_image.view.*
import kotlinx.android.synthetic.main.fragment_users.*

class ChatActivity : AppCompatActivity() {
    var userId: String? = null
    var mRootRef: DatabaseReference? = null
    var mAuth: FirebaseAuth? = null
    var mCurrentUserId: String? = null
    var chatAdapter: ChatMessagesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        mAuth = FirebaseAuth.getInstance()

        mRootRef = FirebaseDatabase.getInstance().reference
        mCurrentUserId = mAuth!!.currentUser!!.uid




        //Setup custom bar image
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        if (intent.extras != null) {
            userId = intent.extras.getString("userId")

            var profileImageLink = intent.extras.get("profile").toString()
            supportActionBar!!.title = intent.extras.get("name").toString()

            //Create a LayoutInflater to get the custom bar image
            var inflater = this.getSystemService(android.content.Context.LAYOUT_INFLATER_SERVICE)
                            as LayoutInflater

            var actionBarView = inflater.inflate(R.layout.custom_bar_image, null)
            actionBarView.customBarName.text = intent.extras.get("name").toString()
            Picasso.with(this).load(profileImageLink).into(actionBarView.CustomBarCircleImageView)
            supportActionBar!!.customView = actionBarView


            mRootRef!!.child("Users").child(userId).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot?) {
                     //This is where we get the last time seen and anything else we need!

                }

                override fun onCancelled(databaseError: DatabaseError?) {

                }
            })


            mRootRef!!.child("Chat").child(mCurrentUserId).addValueEventListener(object: ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot?) {
                    if (!dataSnapshot!!.hasChild(userId)) {
                        //Create one then
                        var chatAddMap = HashMap<String, Any>()
                        chatAddMap.put("seen", false)
                        chatAddMap.put("timestamp", ServerValue.TIMESTAMP)


                        //ChatUser Map -- We are adding chat messages in both users objects
                        var chatUserMap = HashMap<String, Any>()
                        chatUserMap.put("Chat/ ${mCurrentUserId} / $userId", chatAddMap)
                        chatUserMap.put("Chat/ ${userId} / $mCurrentUserId", chatAddMap)

                        mRootRef!!.updateChildren(chatUserMap, DatabaseReference.CompletionListener {
                            databaseError, databaseReference ->
                            if (databaseError != null) {
                                //Error!
                                Toast.makeText(this@ChatActivity, databaseError.message.toString(),
                                        Toast.LENGTH_LONG).show()
                               // Log.d("Error", databaseError.message.toString())



                            }else {



                            }


                          })

                        }else {

                    }

                    }

                override fun onCancelled(p0: DatabaseError?) {

                }

                })


            //Send Chat messages
            chatSendButton.setOnClickListener {

                sendMessage()

            }


            }//End if Intent





            //To save data, we just get all of this information from our intent!


//            mRootRef!!.child("Users").child(userId).addListenerForSingleValueEvent(object:
//            ValueEventListener{
//                //Since we're only retrieving a single child, use ..ForSingleValueEvent
//                override fun onDataChange(dataSnapshot: DataSnapshot?) {
//                    var chatUserName = dataSnapshot!!.child("display_name").value.toString()
//
//                    supportActionBar!!.title = chatUserName
//
//
//
//                }
//
//                override fun onCancelled(databaseError: DatabaseError?) {
//
//                }
//            })


        }

    private fun sendMessage() {
        var message = chatEditText.text.toString().trim()
        if (!TextUtils.isEmpty(message)) {
            //Add message to database
            var currentUserRef = "Messages/$mCurrentUserId / $userId/"
            var chatUserRef = "Messages/$userId/$mCurrentUserId"

            var userMessagePushDB = mRootRef!!.child("Messages").child(mCurrentUserId)
                    .child(userId).push()

            var pushId = userMessagePushDB!!.key

            var messageMap = HashMap<String, Any>()
            messageMap.put("message", message)
            messageMap.put("send", false)
            messageMap.put("type", "text")
            messageMap.put("time", ServerValue.TIMESTAMP)

            //MessageUserMap
            var messageUserMap = HashMap<String, Any>()
            messageUserMap.put(currentUserRef + "/" + pushId, messageMap)
            messageUserMap.put(chatUserRef + "/" + pushId, messageMap)

            //Setup RecyclerView
             var linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)
             chatRecyclerViewId.setHasFixedSize(true)
             chatRecyclerViewId.layoutManager = linearLayoutManager



            mRootRef!!.updateChildren(messageUserMap, DatabaseReference.CompletionListener {
                databaseError, databaseReference ->
                if (databaseError != null) {
                    Log.d("Error", databaseError.message.toString())
                }else {
                    //All is well - clear the edit text and populate the Recyclerview
                    chatEditText.setText("")


                    //------- Start Old -----------
                    //var linearLayoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL,false)


                    //Populate all messages into recyclerView
                   // chatRecyclerViewId.setHasFixedSize(true)
                   // chatRecyclerViewId.layoutManager = linearLayoutManager

                    /*var messageDBData = */
                   var messageDBData = mRootRef!!.child("Messages").child(mCurrentUserId).child(userId).child(pushId)


                   // chatAdapter = ChatMessagesAdapter(messageDBData , applicationContext)
                   // Log.d("DATA======>", messageDBData.child("message").toString())

                    // chatAdapter = ChatMessagesAdapter(messageDBData, applicationContext)
                    chatAdapter = ChatMessagesAdapter(messageDBData , applicationContext)
                    chatAdapter!!.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver(){
                       override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                           super.onItemRangeInserted(positionStart, itemCount)
                           var messageCount = chatAdapter!!.itemCount
                           Log.d("CCOUNT!!", message.toString())
                           var lastVisiblePos = linearLayoutManager.findLastCompletelyVisibleItemPosition()

                           if (lastVisiblePos == -1 || (positionStart >= (messageCount - 1)
                                   && lastVisiblePos == (positionStart -1))) {
                               chatRecyclerViewId.scrollToPosition(positionStart)

                           }
                       }
                   })


                    chatRecyclerViewId.adapter = chatAdapter


 // -------- End Old -----




                }

            })


        }else {

        }


    }

    override fun onStart() {
        super.onStart()
    }

}


 */

