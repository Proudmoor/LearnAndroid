package com.zpf416.dota2lastmacth;

import android.app.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;


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
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int position) {
                Match match = mMatches.get(position);
                return MatchFragment.newInstance(match.getId());
            }

            @Override
            public int getCount() {
                return mMatches.size();
            }
        });

        UUID matchId = (UUID) getIntent()
                .getSerializableExtra(MatchFragment.EXTRA_MATCH_ID);
        for(int i=0; i < mMatches.size(); i++){
            if(mMatches.get(i).getId().equals(matchId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

}
