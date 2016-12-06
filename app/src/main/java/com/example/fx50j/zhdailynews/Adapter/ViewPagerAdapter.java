package com.example.fx50j.zhdailynews.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by FX50J on 2016/11/10.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragment_list;
    private String[] mtittles;


    public ViewPagerAdapter(FragmentManager fm,List<Fragment> fragment_list,String[] tittles) {
        super(fm);
        this.fragment_list = fragment_list;
        this.mtittles = tittles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment_list.get(position);
    }

    @Override
    public int getCount() {
        return fragment_list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtittles[position];
    }
}
