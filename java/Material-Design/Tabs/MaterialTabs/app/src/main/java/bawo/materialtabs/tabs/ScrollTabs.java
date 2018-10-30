package bawo.materialtabs.tabs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import bawo.materialtabs.R;
import bawo.materialtabs.adapters.ScrollTabsAdapter;
import bawo.materialtabs.fragments.FragmentFive;
import bawo.materialtabs.fragments.FragmentFour;
import bawo.materialtabs.fragments.FragmentOne;
import bawo.materialtabs.fragments.FragmentSix;
import bawo.materialtabs.fragments.FragmentThree;
import bawo.materialtabs.fragments.FragmentTwo;

public class ScrollTabs extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private ScrollTabsAdapter adapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_tabs);

        initialise();

        prepapreDataResource();

        adapter = new ScrollTabsAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initialise() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Simple Scroll Tabs");

         viewPager = findViewById(R.id.viewPager);
         tabLayout = findViewById(R.id.tabs);
    }

    private void prepapreDataResource(){
       addData(new FragmentOne(),"ONE");
       addData(new FragmentTwo(), "TWO");
       addData(new FragmentThree(), "THREE");
       addData(new FragmentFour(),"FOUR");
       addData(new FragmentFive(), "FIVE");
       addData(new FragmentSix(), "SIX");

        addData(new FragmentOne(),"ONE");
        addData(new FragmentTwo(), "TWO");
        addData(new FragmentThree(), "THREE");
        addData(new FragmentFour(),"FOUR");
        addData(new FragmentFive(), "FIVE");
        addData(new FragmentSix(), "SIX");
    }
    private void addData(Fragment fragment, String title){
        fragmentList.add(fragment);
        titleList.add(title);

    }
}
