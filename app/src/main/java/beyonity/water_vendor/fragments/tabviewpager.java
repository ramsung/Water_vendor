package beyonity.water_vendor.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import beyonity.water_vendor.R;


public class tabviewpager extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public tabviewpager(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TodayFragment tab1 = new TodayFragment();
                return tab1;
            case 1:
                WeekFragment tab2 = new WeekFragment();
                return tab2;
            case 2:
                MonthFragment tab3 = new MonthFragment();
                return tab3;
            case 3:
                YearFragment tab4 = new YearFragment();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
