package com.zpf416.dota2lastmacth;

import android.os.HandlerThread;
import android.util.Log;

/**
 * Created by zpff on 2015/9/9.
 */
public class ParseMatchDetail<Token> extends HandlerThread {
    private static final String TAG = "ParseMatchDetail";

    public ParseMatchDetail(){
        super(TAG);
    }

    public void queueMatchDetail(Token token, String url){
        Log.i(TAG, "Got an URL: " + url);
    }
}
