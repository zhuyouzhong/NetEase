package Mypageradapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 祝文 on 2017/8/31.
 */

public class MyPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;
    private Context context;

    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, Context context) {
        super(fm);
        this.fragments = fragments;
        this.context = context;
    }

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }
}
