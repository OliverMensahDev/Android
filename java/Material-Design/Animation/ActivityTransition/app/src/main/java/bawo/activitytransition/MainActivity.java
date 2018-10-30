package bawo.activitytransition;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView   imgStar;
    private TextView    txvShared;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindControl();
        setupWindowAnimations();
        setupToolbar();
    }

    private void bindControl() {
        Button btnExplodeJava   = (Button) findViewById(R.id.explodeJava);
        Button btnExplodeXML    = (Button) findViewById(R.id.explodeXML);
        Button btnSlideJava     = (Button) findViewById(R.id.slideJava);
        Button btnSlideXML      = (Button) findViewById(R.id.slideXML);
        Button btnFadeJava      = (Button) findViewById(R.id.fadeJava);
        Button btnFadeXML       = (Button) findViewById(R.id.fadeXML);

        LinearLayout layoutSharedElement = (LinearLayout) findViewById(R.id.shared_element);

        TextView txvRippleWithBorder            = (TextView) findViewById(R.id.txvRippleWithBorder);
        TextView txvRippleWithoutBorder         = (TextView) findViewById(R.id.txvRippleWithoutBorder);
        TextView txvCustomRippleWithBorder      = (TextView) findViewById(R.id.txvCustomRippleWithBorder);
        TextView txvCustomRippleWithoutBorder   = (TextView) findViewById(R.id.txvCustomRippleWithoutBorder);

        imgStar     = (ImageView) findViewById(R.id.imgStarSharedElement);
        txvShared   = (TextView) findViewById(R.id.txvSharedElement);

        btnExplodeJava.setOnClickListener(this);
        btnExplodeXML.setOnClickListener(this);
        btnSlideJava.setOnClickListener(this);
        btnSlideXML.setOnClickListener(this);
        btnFadeJava.setOnClickListener(this);
        btnFadeXML.setOnClickListener(this);
        layoutSharedElement.setOnClickListener(this);

        txvRippleWithBorder.setOnClickListener(this);
        txvRippleWithoutBorder.setOnClickListener(this);
        txvCustomRippleWithBorder.setOnClickListener(this);
        txvCustomRippleWithoutBorder.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning back to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT); // Use START if using right - to - left locale
        slideTransition.setDuration(1000);

        getWindow().setReenterTransition(slideTransition);  // When MainActivity Re-enter the Screen
       // getWindow().setExitTransition(slideTransition);     // When MainActivity Exits the Screen

        // For overlap of Re Entering Activity - MainActivity.java and Exiting TransitionActivity.java
        getWindow().setAllowReturnTransitionOverlap(false);
    }

    protected void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Transition Animation");
        setSupportActionBar(toolbar);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.explodeJava: {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                Intent i = new Intent(MainActivity.this, TransitionActivity.class);
                i.putExtra(Constants.KEY_TYPE, Constants.AnimType.ExplodeJava);
                i.putExtra(Constants.KEY_NAME, "Explode By Code");
                i.putExtra(Constants.KEY_TITLE, "Expolde Animation");
                startActivity(i, options.toBundle());
                break;
            }

            case R.id.explodeXML: {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                Intent i = new Intent(MainActivity.this, TransitionActivity.class);
                i.putExtra(Constants.KEY_TYPE, Constants.AnimType.ExplodeXML);
                i.putExtra(Constants.KEY_NAME, "Explode By XML");
                i.putExtra(Constants.KEY_TITLE, "Expolde Animation");
                startActivity(i, options.toBundle());
                break;
            }

            case R.id.slideJava : {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                Intent i = new Intent(MainActivity.this, TransitionActivity.class);
                i.putExtra(Constants.KEY_TYPE, Constants.AnimType.SlideJava);
                i.putExtra(Constants.KEY_NAME, "Slide By Java");
                i.putExtra(Constants.KEY_TITLE, "Slide Animation");
                startActivity(i, options.toBundle());
                break;
            }

            case R.id.slideXML : {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                Intent i = new Intent(MainActivity.this, TransitionActivity.class);
                i.putExtra(Constants.KEY_TYPE, Constants.AnimType.SlideXML);
                i.putExtra(Constants.KEY_NAME, "Slide By XML");
                i.putExtra(Constants.KEY_TITLE, "Slide Animation");
                startActivity(i, options.toBundle());
                break;
            }

            case R.id.fadeJava : {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                Intent i = new Intent(MainActivity.this, TransitionActivity.class);
                i.putExtra(Constants.KEY_TYPE, Constants.AnimType.FadeJava);
                i.putExtra(Constants.KEY_NAME, "Fade By Java");
                i.putExtra(Constants.KEY_TITLE, "Fade Animation");
                startActivity(i, options.toBundle());
                break;
            }

            case R.id.fadeXML : {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
                Intent i = new Intent(MainActivity.this, TransitionActivity.class);
                i.putExtra(Constants.KEY_TYPE, Constants.AnimType.FadeXML);
                i.putExtra(Constants.KEY_NAME, "Fade By XML");
                i.putExtra(Constants.KEY_TITLE, "Fade Animation");
                startActivity(i, options.toBundle());
                break;
            }

            case R.id.shared_element : {
                Pair[] pair = new Pair[2];

                pair[0] = new Pair<View, String>(imgStar, "star");
                pair[1] = new Pair<View, String>(txvShared, "text_shared");

                //noinspection unchecked
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pair);
                Intent i = new Intent(MainActivity.this, SharedElementActivity.class);
                startActivity(i, options.toBundle());

                break;
            }
        }
    }
}
