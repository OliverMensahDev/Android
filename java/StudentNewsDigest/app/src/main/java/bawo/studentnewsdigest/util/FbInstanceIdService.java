package bawo.studentnewsdigest.util;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import bawo.studentnewsdigest.R;

public class FbInstanceIdService  extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        sendTokenToServer(token);
    }

    private void sendTokenToServer(String token) {
        FirebaseUtil.setupDatabase("users")
                .child(FirebaseUtil.setupAuth().getCurrentUser().getUid())
                .child(getString(R.string.field_messaging_token))
                .setValue(token);
    }
}
