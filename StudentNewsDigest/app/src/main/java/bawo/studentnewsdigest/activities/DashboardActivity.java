package bawo.studentnewsdigest.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import bawo.studentnewsdigest.fragments.NavigationDrawerFragment;
import bawo.studentnewsdigest.R;
import bawo.studentnewsdigest.util.FirebaseUtil;

public class DashboardActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();

    }

    private void init() {
        setupToolbar();
        setupDrawer();
        checkingAdmin(FirebaseUtil.setupAuth().getUid());

    }

    private void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
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
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        RecyclerAdapter adapter = new RecyclerAdapter(this, LandscapeData.getData());
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());// the recyclerview  uses this by default
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
//
//    private void getCourses(){
//        showDialog();
//        mDatabaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    for(DataSnapshot singleSnapshot:  dataSnapshot.getChildren()){
//                        Course course = singleSnapshot.getValue(Course.class);
//                        Log.d("data", course.toString());
//                        courses.add(course);
//                        adapter.notifyDataSetChanged();
//                    }
//                }
//                hideDialog();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                hideDialog();
//            }
//        });
//    }
}
