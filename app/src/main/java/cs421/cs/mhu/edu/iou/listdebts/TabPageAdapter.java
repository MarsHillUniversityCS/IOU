package cs421.cs.mhu.edu.iou.listdebts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by mgilbert on 12/12/17.
 */

public class TabPageAdapter extends FragmentPagerAdapter {

    public TabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new ListDebtorsFragment();
            case 1: return new ListDebtorsFragment();
            case 2: return new ListDebtorsFragment();
            default: return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "Their debts";
            case 1:
                return "My Debts";
            case 2:
                return "Contacts";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
