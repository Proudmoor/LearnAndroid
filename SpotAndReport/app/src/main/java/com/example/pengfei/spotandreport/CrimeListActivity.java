package com.example.pengfei.spotandreport;


import android.support.v4.app.Fragment;

/**
 * Created by pengfei on 15-5-12.
 */
public class CrimeListActivity extends SingleFragmentActivity {
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
