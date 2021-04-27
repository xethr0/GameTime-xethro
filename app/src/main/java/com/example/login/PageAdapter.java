package com.example.sample;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.login.Calendar;
import com.example.login.GameFeed;
import com.example.login.Profile;
import com.example.login.SearchBar;

public class PageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;



    public PageAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs= numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Profile();
            case 1:
                return new Calendar();
            case 2:
                return new GameFeed();
            case 3:
                return new SearchBar();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
