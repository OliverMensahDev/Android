package bawo.adaptiveui.activities;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import bawo.adaptiveui.InterCommunicator;
import bawo.adaptiveui.R;
import bawo.adaptiveui.adapter.PostAdapter;
import bawo.adaptiveui.data.PostData;
import bawo.adaptiveui.fragments.DetailsFragment;
import bawo.adaptiveui.model.Post;

public class MainActivity extends AppCompatActivity implements InterCommunicator {
    private boolean isDualPane;
    private DetailsFragment detailsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        detailsFragment = (DetailsFragment) fragmentManager.findFragmentById(R.id.details_fragment);
        View view = findViewById(R.id.details_fragment);
        isDualPane = view != null && view.getVisibility() == View.VISIBLE;

        if(isDualPane){
            Post post = PostData.postList().get(0);
            detailsFragment.setViews(post.getPostDescription(), post.getPostName() );
        }
    }

    @Override
    public void sendCurrentItemPosition(int position) {
        if(isDualPane){
            Post post = PostData.postList().get(position);
            detailsFragment.setViews(post.getPostDescription(), post.getPostName() );
        }else{
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }
}
