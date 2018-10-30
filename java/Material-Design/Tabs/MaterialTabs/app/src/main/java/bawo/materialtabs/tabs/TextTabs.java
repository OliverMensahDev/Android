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
import bawo.materialtabs.adapters.TextIconTabsAdapter;
import bawo.materialtabs.adapters.TextTabsAdapter;
import bawo.materialtabs.fragments.FragmentOne;
import bawo.materialtabs.fragments.FragmentThree;
import bawo.materialtabs.fragments.FragmentTwo;

public class TextTabs extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private TextIconTabsAdapter adapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_tabs);

        initialise();

        prepapreDataResource();

        adapter = new TextIconTabsAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initialise() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Simple Text Tabs");

         viewPager = findViewById(R.id.viewPager);
         tabLayout = findViewById(R.id.tabs);
    }

    private void prepapreDataResource(){
       addData(new FragmentOne(),"ONE");
       addData(new FragmentTwo(), "TWO");
       addData(new FragmentThree(), "THREE");

    }
    private void addData(Fragment fragment, String title){
        fragmentList.add(fragment);
        titleList.add(title);

    }
}
