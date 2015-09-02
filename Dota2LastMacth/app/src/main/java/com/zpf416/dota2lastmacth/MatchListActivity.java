package com.zpf416.dota2lastmacth;

import android.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MatchListActivity extends SingleFragmentActivity {

   @Override
    protected Fragment createFragment(){
       return new MatchListFragment ();
   }
}
