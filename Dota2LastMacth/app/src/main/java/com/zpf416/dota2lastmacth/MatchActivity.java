package com.zpf416.dota2lastmacth;


import android.app.Fragment;

/**
 * Created by pengfei on 15-9-2.
 */
public class MatchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        return new MatchFragment();
    }
}
