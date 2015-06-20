package com.zpf416.glsample;

import android.content.Context;
import android.opengl.GLSurfaceView;
/**
 * Created by pengfei on 15-6-20.
 */
public class MyGLSurfaceView extends GLSurfaceView {
    private final MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context){
        super(context);

        setEGLContextClientVersion(2);

        mRenderer = new MyGLRenderer();

        setRenderer(mRenderer);
    }

}
