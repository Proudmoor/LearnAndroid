package com.zpf416.dota2lastmacth;


import android.app.Fragment;

import java.util.UUID;

/**
 * Created by pengfei on 15-9-2.
 */
public class MatchActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment(){
        UUID matchId = (UUID)getIntent()
                .getSerializableExtra(MatchFragment.EXTRA_MATCH_ID);

        return MatchFragment.newInstance(matchId);
    }
}
