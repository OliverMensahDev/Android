package chatapp.gohool.com.whatsupchatapp.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import chatapp.gohool.com.whatsupchatapp.fragments.ChatFragment
import chatapp.gohool.com.whatsupchatapp.fragments.UsersFragment

/**
 * Created by paulodichone on 7/17/17.
 */
class SectionsPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {

                return UsersFragment()
            }
            1 -> {

                return ChatFragment()
            }
//            2 -> {
//
//
//            }

        }
        return null!!

    }

    override fun getCount(): Int {
       return 2
    }

    override fun getPageTitle(position: Int): CharSequence {

        when(position){
            0 -> return "FRIENDS"
            1 -> return "CHATS"

        }
        return null!!

    }
}