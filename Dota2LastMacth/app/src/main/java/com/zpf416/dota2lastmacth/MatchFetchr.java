package com.zpf416.dota2lastmacth;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by zpff on 2015/9/9.
 */
public class MatchFetchr {
    public static final String TAG = "MatchFetch";
    public static final String ENDPOINT = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/";
    public static final String API_KEY = "809DF5B077AC15B512C4ECFA836470B0";
    public static final String ENDPOINT_DT = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/";


    byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if(connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                return null;
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while((bytesRead = in.read(buffer)) > 0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        }finally {
            connection.disconnect();
        }
    }
    public String getUrl(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }


    public ArrayList<Match> fetchMatchHistory(String accountId){
        ArrayList<Match> matches = new ArrayList<>();

        try{
            String url = Uri.parse(ENDPOINT).buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("account_id", accountId)
                    .build().toString();
            String jsonString = getUrl(url);
            Log.i(TAG, "Recevied json: "+ jsonString );
            parseHistory(matches, jsonString);
        } catch(IOException ioe){
            Log.e(TAG, "Failed to fetch items", ioe);
        }

        return matches;
    }
    void parseHistory(ArrayList<Match> matches,String jsonString){
        try {
            JSONObject result = (new JSONObject(jsonString)).getJSONObject("result");
            JSONArray matchArray = result.getJSONArray("matches");
            for (int i = 0; i < matchArray.length(); i++) {
                Match m = new Match();
                JSONObject jo = matchArray.getJSONObject(i);
                m.setMatchId(jo.getLong("match_id"));
                m.setMatchDate(jo.getLong("start_time"));
                m.setPlayers(jo.getJSONArray("players"));
                matches.add(m);
                Log.i(TAG, jo.getString("start_time"));
            }
            Log.i(TAG, "length of MatchArray :" + matchArray.length());
        } catch(JSONException jse){
            Log.e(TAG, "Parse History from JsonString failed", jse);
        }
    }

//    public ArrayList<Player> fetchMatchDetail(String matchID){
//        JSONArray playersJSON = null;
//        try{
//            String url = Uri.parse(ENDPOINT_DT).buildUpon()
//                    .appendQueryParameter("key", API_KEY)
//                    .appendQueryParameter("match_id", matchID)
//                    .build().toString();
//            String jsonString = getUrl(url);
//            Log.i(TAG, "Received json: "+ jsonString);
//            parseMatchDetail(playersJSON,jsonString );
//        } catch(IOException ioe){
//            Log.e(TAG, "Parse Match Deatail from jsonString failed",ioe );
//        }
//
//        return playersJSON;
//    }
//
//    void parseMatchDetail(JSONArray players,String jsonString){
//        try{
//            JSONObject result = (new JSONObject(jsonString)).getJSONObject("result");
//        }
//    }


}
