package bawo.studentnewsdigest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.Collections;

import bawo.studentnewsdigest.adapters.ArticleAdapter;
import bawo.studentnewsdigest.fragments.NavigationDrawerFragment;
import bawo.studentnewsdigest.R;
import bawo.studentnewsdigest.model.Article;
import bawo.studentnewsdigest.util.FirebaseUtil;

public class DashboardActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private ArrayList<Article> articles;
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initFCM();
        init();
    }

    private void init() {
        articles = new ArrayList<>();
        setupWideget();
    }

    private void setupWideget() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        progressBar = findViewById(R.id.dashboard_progressBar);

        setupDrawer();
        setupRecyclerView();
    }

    private void setupMenu() {
        toolbar.inflateMenu(R.menu.menus);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = null;
                switch (item.getItemId()) {
                    case R.id.add:
                        intent = new Intent(DashboardActivity.this, PostArticle.class);
                        break;

                    case R.id.review:

                        break;
                }
               startActivity(intent);
                return false;
            }
        });
    }


    private void setupDrawer() {
        NavigationDrawerFragment fragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drwr_fragment);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        fragment.setUpDrawer(R.id.nav_drwr_fragment, drawerLayout, toolbar);
    }


    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.dashboard_recyclerView);
        adapter = new ArticleAdapter(this, articles);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getCourses();
    }

    private void checkingAdmin(String uid) {
        FirebaseUtil.setupDatabase("editors").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    setupMenu();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void getCourses(){
        showDialog();
        checkingAdmin(FirebaseUtil.setupAuth().getUid());
        FirebaseUtil.setupDatabase("articles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot singleSnapshot:  dataSnapshot.getChildren()){
                        Article article = singleSnapshot.getValue(Article.class);
                        Log.d("article", article.toString());
                        articles.add(article);
                        Collections.reverse(articles);
                        adapter.notifyDataSetChanged();
                    }
                }
                hideDialog();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                hideDialog();
            }
        });
    }

    private void showDialog(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideDialog(){
        if(progressBar.getVisibility() == View.VISIBLE){
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private  void initFCM(){
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
