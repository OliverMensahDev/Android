package bawo.activitytransition;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.TextView;

public class TransitionActivity extends AppCompatActivity {

    Constants.AnimType type;
    String toolbarTitle, animName;
    TextView mTxvAnimName;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // inside your activity (if you did not enable transitions in your theme)
        // For AppCompat getWindow must be called before calling super.OnCreate
        // Must be called before setContentView
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition);

        type = (Constants.AnimType) getIntent().getSerializableExtra(Constants.KEY_TYPE);
        toolbarTitle = getIntent().getExtras().getString(Constants.KEY_TITLE);
        animName = getIntent().getExtras().getString(Constants.KEY_NAME);

        setUpAnimation();

        bindControl();

        setupToolbar();

        // For overlap between Exiting  MainActivity.java and Entering TransitionActivity.java
        getWindow().setAllowEnterTransitionOverlap(false);
    }

    private void bindControl() {
        findViewById(R.id.exit_button).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });

        mTxvAnimName = (TextView) findViewById(R.id.animName);
        mTxvAnimName.setText(animName);
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(toolbarTitle);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onSupportNavigateUp() {
        finishAfterTransition();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setUpAnimation() {

        switch (type) {

            case ExplodeJava: { // For Explode By Code

                Explode enterTransition = new Explode();
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }

            case ExplodeXML: { // For Explode By XML

                Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.explode);
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }

            case SlideJava: { // For Slide By Code

                Slide enterTransition = new Slide();
                enterTransition.setSlideEdge(Gravity.RIGHT);
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                enterTransition.setInterpolator(new AnticipateOvershootInterpolator());
                getWindow().setEnterTransition(enterTransition);
                break;
            }

            case SlideXML: { // For Slide by XML

                Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.slide);
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_very_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }

            case FadeJava: { // For Fade By Code

                Fade enterTransition = new Fade();
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }

            case FadeXML: { // For Fade by XML

                Transition enterTransition = TransitionInflater.from(this).inflateTransition(R.transition.fade);
                enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
                getWindow().setEnterTransition(enterTransition);
                break;
            }
        }

    }
}