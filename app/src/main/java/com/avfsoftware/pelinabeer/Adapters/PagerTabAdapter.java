package com.avfsoftware.pelinabeer.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.avfsoftware.pelinabeer.Fragments.FavoritesFragment;
import com.avfsoftware.pelinabeer.Fragments.MovieListFragment;

public class PagerTabAdapter extends FragmentStatePagerAdapter {

    int TabNumber;
    public PagerTabAdapter(FragmentManager fm) {
        super(fm);
//        this.TabNumber = TabNum;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                 return new MovieListFragment();
            case 1:
                return new FavoritesFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Movies";
            case 1: return "Favorites";
            default: return null;
        }
    }
}
