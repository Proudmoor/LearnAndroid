package com.example.pengfei.spotandreport;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by pengfei on 15-5-12.
 */
public abstract class SingleFragmentActivity extends Activity {
    protected abstract Fragment createFragment();
    //增加了一个抽象的类，用来建立Fragment
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
        if(fragment == null){
            fragment = createFragment(); // 原来的是具体的CrimeFragment()
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }
}
