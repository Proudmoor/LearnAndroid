package com.example.pengfei.ndk_setup;

import android.app.Activity;
import android.os.Bundle;

import jni.Natives;


public class MainActivity extends Activity {

    {
        System.loadLibrary("ch02");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            String[] argv = {"MyLib", "argv1", "argv2"};

            Natives.LibMain(argv);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}
