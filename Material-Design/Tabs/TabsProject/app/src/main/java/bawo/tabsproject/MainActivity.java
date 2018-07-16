package bawo.tabsproject;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import bawo.tabsproject.adapter.TabsAdapter;
import bawo.tabsproject.fragments.FragmentCalls;
import bawo.tabsproject.fragments.FragmentChat;
import bawo.tabsproject.fragments.FragmentStatus;
import bawo.tabsproject.fragments.FragmentVideo;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    private ViewPager viewPager;
    private TabsAdapter adapter;
    private TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        prepapreDataResource();

        adapter = new TabsAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabIcons();
    }

    private void init() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("WhatsApp");

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }

    private void prepapreDataResource(){
        addData(new FragmentVideo(),"");
        addData(new FragmentChat(), "chats");
        addData(new FragmentStatus(), "Status");
        addData(new FragmentCalls(), "Calls");

    }
    private void addData(Fragment fragment, String title){
        fragmentList.add(fragment);
        titleList.add(title);
    }

    private void setTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.camera);

    }

}
