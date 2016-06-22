package com.restaurantapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.restaurantapplication.fragments.DinnerFragment;
import com.restaurantapplication.fragments.BreakFragment;
import com.restaurantapplication.fragments.LunchFragment;
import com.restaurantapplication.fragments.SnacksFragment;



public class MyPagerAdapter extends FragmentPagerAdapter {
    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public MyPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;
        //Returning the current tabs
        switch (position) {
            case 0:
                fragment = new BreakFragment();
                return fragment;
            case 1:
                fragment = new LunchFragment();
                return fragment;
            case 2:
                fragment = new SnacksFragment();
                return fragment;
            case 3:
                fragment = new DinnerFragment();
                return fragment;
            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }

}
