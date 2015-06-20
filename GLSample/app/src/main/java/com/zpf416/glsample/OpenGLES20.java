package com.zpf416.glsample;

import android.opengl.GLSurfaceView;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class OpenGLES20 extends ActionBarActivity {

    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLView  = new MyGLSurfaceView(this);

        setContentView(mGLView);
    }

    @Override
    protected void onPause(){
        super.onPause();

        mGLView.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();

        mGLView.onResume();
    }
}
