package e.olivermensah.fragmentstatepageradapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import e.olivermensah.fragmentstatepageradapter.fragments.FragmentFive;
import e.olivermensah.fragmentstatepageradapter.fragments.FragmentFour;
import e.olivermensah.fragmentstatepageradapter.fragments.FragmentOne;
import e.olivermensah.fragmentstatepageradapter.fragments.FragmentSix;
import e.olivermensah.fragmentstatepageradapter.fragments.FragmentThree;
import e.olivermensah.fragmentstatepageradapter.fragments.FragmentTwo;


public class CustomAdapter extends FragmentStatePagerAdapter {
    private  final int ITEMS = 6;

    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentOne();

            case 1:
                return new FragmentTwo();

            case 2:
                return new FragmentThree();

            case 3:
                return new FragmentFour();

            case 4:
                return new FragmentFive();

            case 5:
                return new FragmentSix();

            default:
                return new FragmentOne();
        }
    }

    @Override
    public int getCount() {
        return  ITEMS;
    }
}
