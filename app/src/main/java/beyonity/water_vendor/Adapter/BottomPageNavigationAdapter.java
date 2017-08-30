package beyonity.water_vendor.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import beyonity.water_vendor.fragments.mainContent;


public class BottomPageNavigationAdapter extends FragmentPagerAdapter {

    private int size;

    public BottomPageNavigationAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size = size;
    }

    @Override
    public Fragment getItem(int position) {
        return mainContent.newInstance(position + "");
    }

    @Override
    public int getCount() {
        return size;
    }
}
