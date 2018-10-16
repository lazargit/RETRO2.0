package com.shamildev.retro.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shamil Lazar on 12.10.2018.
 */
/*

    Class for store fragments for tabs

 */
public class SectionPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "SectionPageAdapter";
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
    public void addFragment(Fragment fragment){
        this.mFragmentList.add(fragment);

    }
}
