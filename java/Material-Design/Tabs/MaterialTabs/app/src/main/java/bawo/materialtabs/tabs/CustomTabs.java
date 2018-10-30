package bawo.materialtabs.tabs;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import bawo.materialtabs.R;
import bawo.materialtabs.adapters.CustomTabsAdapter;
import bawo.materialtabs.adapters.TextIconTabsAdapter;
import bawo.materialtabs.fragments.FragmentOne;
import bawo.materialtabs.fragments.FragmentThree;
import bawo.materialtabs.fragments.FragmentTwo;

public class CustomTabs extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();
    private ViewPager viewPager;
    private CustomTabsAdapter adapter;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_tabs);

        initialise();

        prepapreDataResource();

        adapter = new CustomTabsAdapter(getSupportFragmentManager(), fragmentList, titleList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        setCustomViewForTabs();
    }

    private void setCustomViewForTabs() {
        setCustomViewForTab(0, "WALL", "TAB ONE");
        setCustomViewForTab(1, "WALL 1", "TAB TWO");
        setCustomViewForTab(2, "WALL 2", "TAB THREE");
    }

    private void setCustomViewForTab(int position, String title, String subTitle){
        LinearLayout tabView= (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_item, null);
        TextView txtViewTitle = tabView.findViewById(R.id.tabTextTitle);
        txtViewTitle.setText(title);

        TextView txtViewSubTitle = tabView.findViewById(R.id.tabTextSubTitle);
        txtViewSubTitle.setText(subTitle);

        tabLayout.getTabAt(position).setCustomView(tabView);



    }

    private void initialise() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Custom Tabs");

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
