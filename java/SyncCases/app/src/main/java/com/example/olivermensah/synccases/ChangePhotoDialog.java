package com.example.olivermensah.synccases;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChangePhotoDialog extends DialogFragment {
    private static final String TAG = "ChangePhotoDialog";

    public static final int  CAMERA_REQUEST_CODE = 5467;//random number

    public interface OnPhotoReceivedListener{
        public void getImageBitmap(Bitmap bitmap);
    }

    OnPhotoReceivedListener mOnPhotoReceived;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_changephotodialog, container, false);



        //Initialize the textview for capture
        TextView takePhoto = (TextView) view.findViewById(R.id.dialogOpenCamera);
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: starting camera");
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            Log.d(TAG, "onActivityResult: done taking a photo.");
            Bitmap bitmap;
            bitmap = (Bitmap) data.getExtras().get("data");
            mOnPhotoReceived = (OnPhotoReceivedListener) getActivity();
            mOnPhotoReceived.getImageBitmap(bitmap);
            getDialog().dismiss();

        }
    }

    @Override
    public void onAttach(Context context) {
        try{
            mOnPhotoReceived = (OnPhotoReceivedListener) getActivity();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException", e.getCause() );
        }
        super.onAttach(context);
    }
}
