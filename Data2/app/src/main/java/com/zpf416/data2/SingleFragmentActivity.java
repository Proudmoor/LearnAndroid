package com.zpf416.data2;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

/**
 * Created by zpff on 2015/9/2.
 */
public abstract class SingleFragmentActivity extends FragmentActivity{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstaneceState){
        super.onCreate(savedInstaneceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
