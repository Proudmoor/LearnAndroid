package com.example.zpff.ndk_c;


import android.app.Activity;
import android.os.Bundle;

import jni.Natives;


public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.loadLibrary("ch02");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String[] argv = {"MyLib", "arg1", "arg2"};

            Natives.LibMain(argv);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
