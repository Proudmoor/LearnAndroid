package com.zpf416.dota2lastmacth;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;


public class MatchPagerActivity extends Activity {
    private ViewPager mViewPager;
    private ArrayList<Match> mMatches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        mMatches = MatchPool.get(this).getMatches();

        FragmentManager fm = getFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter() {
            @Override
            public Fragment getItem(int position) {
                Match match = mMatches.get(position);
                return MatchFragment.newInstance(match.getId());
            }

            @Override
            public int getCount() {
                return 0;
            }
        });
    }

}
