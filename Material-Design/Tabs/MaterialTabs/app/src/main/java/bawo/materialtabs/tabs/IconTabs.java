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
import bawo.materialtabs.adapters.IconTabsAdapter;
import bawo.materialtabs.fragments.FragmentFive;
import bawo.materialtabs.fragments.FragmentFour;
import bawo.materialtabs.fragments.FragmentOne;
import bawo.materialtabs.fragments.FragmentSix;
import bawo.materialtabs.fragments.FragmentThree;
import bawo.materialtabs.fragments.FragmentTwo;

public class IconTabs extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();

    private ViewPager viewPager;
    private IconTabsAdapter adapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_tabs);

        initialise();

        prepapreDataResource();

        adapter = new IconTabsAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setTabIcons();
    }

    private void setTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.facebook);
        tabLayout.getTabAt(1).setIcon(R.drawable.googleplus);
        tabLayout.getTabAt(2).setIcon(R.drawable.instagram);
        tabLayout.getTabAt(3).setIcon(R.drawable.linkedin);
        tabLayout.getTabAt(4).setIcon(R.drawable.whatsapp);
        tabLayout.getTabAt(5).setIcon(R.drawable.twitter);
    }

    private void initialise() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Simple Icon Tabs");

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabs);
    }

    private void prepapreDataResource(){
        fragmentList.add(new FragmentOne());
        fragmentList.add(new FragmentTwo());
        fragmentList.add(new FragmentThree());
        fragmentList.add(new FragmentFour());
        fragmentList.add(new FragmentFive());
        fragmentList.add(new FragmentSix());

    }

}
