package com.example.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TabActivityMenu  extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem profile, calendar, gameFeed;
    public PageAdapter pageAdapter;

// this is a test bro!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        profile = (TabItem) findViewById(R.id.profile);
        calendar = (TabItem) findViewById(R.id.calendar);
        gameFeed = (TabItem) findViewById(R.id.gameFeed);
        viewPager = findViewById(R.id.viewpager);

        pageAdapter = new PageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    pageAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 0) {
                    pageAdapter.notifyDataSetChanged();
                } else if (tab.getPosition() == 0) {
                    pageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }
}
