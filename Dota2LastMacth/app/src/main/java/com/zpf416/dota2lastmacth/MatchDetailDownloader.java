package com.zpf416.dota2lastmacth;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zpff on 2015/9/11.
 */
public class MatchDetailDownloader<Token> extends HandlerThread {
    private static final String TAG = "MatchDetailDownloader";
    private static final int MESSAGE_DOWNLOAD = 0;
    private static final int WIN_LOSE_COMEOUT = 0;
    static Handler mHandler;
    Map<Token, String> requestMap = Collections.synchronizedMap(new HashMap<Token, String>());

    Handler mResponseHandler;
    Listener<Token> mListener;

    public interface Listener<Token>{
        void onMatchDetailDownloaded(Token token, JSONObject jo);
    }

    public void setListener(Listener<Token> listener){
        mListener = listener;
    }


    public MatchDetailDownloader(Handler responseHandler) {
        super(TAG);
        mResponseHandler = responseHandler;
    }
    @SuppressLint("HandlerLeak")
    @Override
    protected void onLooperPrepared(){
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == MESSAGE_DOWNLOAD){
                    @SuppressWarnings("unckecked")
                    Token token = (Token) msg.obj;
                    Log.i(TAG, "Got a request for url: " + requestMap.get(token));
                    handleRequest(token);
                }
            }
        };
    }

    public void queueMatchDetail(Token token, String url){
        Log.i(TAG, "Got an URL: " + url );
        requestMap.put(token, url);

        mHandler.obtainMessage(MESSAGE_DOWNLOAD, token)
                .sendToTarget();
    }

    private void handleRequest(final Token token){
        try{
            final String url = requestMap.get(token);
            if(url == null)
                return;

            String  detailJsonString = new MatchFetchr().getUrl(url);
            Log.i(TAG, "Get json String from: " + url);
            Log.i(TAG, "Get detailjson:" + detailJsonString);
            final JSONObject detailJSON = (new JSONObject(detailJsonString)).getJSONObject("result");
            Log.i(TAG, "get an detail JSON:" + detailJSON.getLong("duration"));
            mResponseHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(requestMap.get(token) != url)
                        return;

                    requestMap.remove(token);
                    mListener.onMatchDetailDownloaded(token, detailJSON);
                }
            });

        } catch (IOException ioe){
            Log.e(TAG, "Error downloading json matchdetail.", ioe);
        } catch (JSONException jse){
            Log.e(TAG, "Error new JSONObject.");
        }

    }

    public void clearQueue(){
        mHandler.removeMessages(MESSAGE_DOWNLOAD);
        requestMap.clear();
    }
}
