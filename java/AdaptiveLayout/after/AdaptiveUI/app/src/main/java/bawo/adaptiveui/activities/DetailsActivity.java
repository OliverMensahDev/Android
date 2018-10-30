package bawo.adaptiveui.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bawo.adaptiveui.R;
import bawo.adaptiveui.data.PostData;
import bawo.adaptiveui.fragments.DetailsFragment;
import bawo.adaptiveui.model.Post;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            DetailsFragment detailFragmentB = (DetailsFragment) fragmentManager.findFragmentById(R.id.details_fragment);
            Post post = PostData.postList().get(bundle.getInt("position"));
            detailFragmentB.setViews(post.getPostDescription(), post.getPostName());
        }
    }
}
