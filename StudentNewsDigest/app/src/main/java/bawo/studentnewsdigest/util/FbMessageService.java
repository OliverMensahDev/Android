package bawo.studentnewsdigest.util;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FbMessageService extends FirebaseMessagingService {
    private static final String TAG = "FbMessageService";

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String notificationBody ="", notificationTitle="", notificationData = "";
        try {
            notificationData = remoteMessage.getData().toString();
            notificationTitle = remoteMessage.getNotification().getTitle();
            notificationBody = remoteMessage.getNotification().getBody();

        }catch (NullPointerException e){
            Log.e(TAG, "onMessageReceived: NullPointerException " + e.getMessage() );
        }

        Log.e(TAG, "onMessageReceived: data " + notificationData );
        Log.e(TAG, "onMessageReceived: notification body " + notificationBody );
        Log.e(TAG, "onMessageReceived: notification title " + notificationTitle );


    }


}
