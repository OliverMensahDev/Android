package bawo.materialtabs.tabs;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import bawo.materialtabs.R;
import bawo.materialtabs.adapters.TextTabsAdapter;
import bawo.materialtabs.fragments.FragmentOne;
import bawo.materialtabs.fragments.FragmentThree;
import bawo.materialtabs.fragments.FragmentTwo;

public class TextIconTabs extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private TextTabsAdapter adapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_tabs);

        initialise();

        prepapreDataResource();

        adapter = new TextTabsAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        setTabIcons();
    }

    private void setTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.facebook);
        tabLayout.getTabAt(1).setIcon(R.drawable.googleplus);
        tabLayout.getTabAt(2).setIcon(R.drawable.instagram);
    }

    private void initialise() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tab With Icon and Text");

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
    }

    private void prepapreDataResource(){
        addData(new FragmentOne(),"One");
        addData(new FragmentTwo(), "Two");
        addData(new FragmentThree(), "Three");

    }
    private void addData(Fragment fragment, String title){
        fragmentList.add(fragment);
        titleList.add(title);

    }
}
