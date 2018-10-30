package wapmass.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ActivityLifeCycle extends AppCompatActivity {
    private final String TAG = "ActivityLifeCycle";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);
        Log.d(TAG, "oncreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d(TAG, "onstart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onresume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onstop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "ondestroy");
    }
}
