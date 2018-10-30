package chatapp.gohool.com.whatsupchatapp.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import chatapp.gohool.com.whatsupchatapp.R
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.squareup.picasso.Picasso
import com.theartofdev.edmodo.cropper.CropImage
import id.zelory.compressor.Compressor
import kotlinx.android.synthetic.main.activity_settings.*
import java.io.ByteArrayOutputStream
import java.io.File

class SettingsActivity : AppCompatActivity() {
    var mDatabase: DatabaseReference? = null
    var mCurrentUser: FirebaseUser? = null
    var mStorageRef: StorageReference? = null

    val  GALLERY_ID: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)


        mStorageRef = FirebaseStorage.getInstance().reference
        mCurrentUser = FirebaseAuth.getInstance().currentUser

        var userId = mCurrentUser?.uid

        mDatabase = FirebaseDatabase.getInstance().reference.child("Users")
                .child(userId)

        mDatabase!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot?) {

                var displayName = dataSnapshot!!.child("display_name").getValue().toString()
                var image = dataSnapshot!!.child("image").getValue().toString()
                var userStatus = dataSnapshot!!.child("user_status").getValue().toString()
                var thumbNail = dataSnapshot!!.child("thumb_image").getValue().toString()

                settingsDisplayName.text = displayName
                settingsStatusText.text = userStatus

                if (!image.equals("default")) {
                    Picasso.with(applicationContext).load(image).placeholder(R.drawable.profile_img).into(settingsProfile)

                }


            }

            override fun onCancelled(databaseError: DatabaseError?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

           })


        settingsChangeStatus.setOnClickListener {
            //Go to status Activity
            var intent = Intent(this, StatusActivity::class.java)
            var status = settingsStatusText.text.toString().trim()
            intent.putExtra("status", status)
            startActivity(intent)
        }

        settingsChangeImgButton.setOnClickListener {

            var galleryIntent = Intent()
            galleryIntent.type = "image/*"
            galleryIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(galleryIntent, "SELECT IMAGE"), GALLERY_ID)

        }
        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == GALLERY_ID && resultCode == Activity.RESULT_OK) {
            var image: Uri = data!!.data

            CropImage.activity(image)
                    .setAspectRatio(1,1) // to make it small as thumbnail for memory purposes
                    .start(this)
        }

        if (requestCode === CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)

            if (resultCode === Activity.RESULT_OK) {
                val resultUri = result.uri

                var userId = mCurrentUser!!.uid
                var thumbFile = File(resultUri.path)


                var thumbBitmap = Compressor(this)
                        .setMaxWidth(200)
                        .setMaxHeight(200)
                        .setQuality(65)
                        .compressToBitmap(thumbFile)

                //How to upload a bitmap to Firebase?
                var byteArray = ByteArrayOutputStream()
                thumbBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArray)
                var thumbByteArray: ByteArray
                thumbByteArray = byteArray.toByteArray()


                //Let's use the userId for the image name, instead of the random character function.
                //Either way works, but this is best.
                var filePath = mStorageRef!!.child("chat_profile_images").child(userId + ".jpg")
                //create another directory for thumbnails
                var thumbFilePath = mStorageRef!!.child("chat_profile_images")
                        .child("thumbs").child(userId + ".jpg")

                filePath.putFile(resultUri).addOnCompleteListener {
                    task: Task<UploadTask.TaskSnapshot> ->
                    if (task.isSuccessful) {

                        //Let's get the pic url
                        var downloadUrl = task.result.downloadUrl.toString()

                        //Create our upload task ( from firebase docs)
                        var uploadTask: UploadTask = thumbFilePath.putBytes(thumbByteArray)
                        uploadTask.addOnCompleteListener {
                            task: Task<UploadTask.TaskSnapshot> ->
                            //Get the thumb download url
                            var thumbUrl = task.result.downloadUrl.toString()

                            if (task.isSuccessful) {

                                var updateObj = HashMap<String, Any>()
                                updateObj.put("image", downloadUrl)
                                updateObj.put("thumb_image", thumbUrl)

                                //we then save the profile image
                                //used to be mDatabase!.child("image").setValue()..."
                                mDatabase!!.updateChildren(updateObj).addOnCompleteListener {
                                    task: Task<Void> ->
                                    if (task.isSuccessful) {
                                        Toast.makeText(this, "Profile set!", Toast.LENGTH_LONG)
                                                .show()


                                    }else{

                                    }
                                }

                            }else {

                            }

                        }

//                          mDatabase!!.child("image").setValue(downloadUrl).addOnCompleteListener {
//                              task: Task<Void> ->
//                               if (task.isSuccessful) {
//                                   Toast.makeText(this, "Profile set!", Toast.LENGTH_LONG)
//                                           .show()
//
//
//                               }else{
//
//                               }
//                          }
                        Toast.makeText(this, "Image Saved!", Toast.LENGTH_LONG)
                                .show()

                    }else {

                        Toast.makeText(this, "Image Not saved!", Toast.LENGTH_LONG)
                                .show()

                    }
                }

            } else if (resultCode === CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }


}
