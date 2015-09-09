package com.zpf416.dota2lastmacth;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zpff on 2015/9/2.
 */
public class Match {
    static SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd，HH:mm");
    private static final String TAG = "MATCH";
    public Match(){
        mId = UUID.randomUUID();
//        try{
//            for(int i = 0; i < mPlayers.length(); i++){
//                JSONObject player = mPlayers.getJSONObject(i);
//                if(player.getLong("account_id") == 138383743)
//                    mHeroPlayed = player.getInt("hero_id");
//            }
//        }catch (JSONException jse){
//            Log.e(TAG, "Get Players failed.", jse);
//        }
    }
    public Long getMatchId() {
        return mMatchId;
    }

    public void setMatchId(Long matchId) {
        mMatchId = matchId;
    }

    public Long getMatchDate() {
        return mMatchDate;
    }

    public void setMatchDate(Long matchDate) {
        mMatchDate = matchDate;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public JSONArray getPlayers() {
        return mPlayers;
    }

    public void setPlayers(JSONArray players) {
        mPlayers = players;
    }

    private Long mMatchId;
    private Long mMatchDate;
    private UUID mId;
    private String mTitle;
    private JSONArray mPlayers;

    public int getHeroPlayed() {
        return mHeroPlayed;
    }

    public void setHeroPlayed(int heroPlayed) {
        mHeroPlayed = heroPlayed;
    }

    private int    mHeroPlayed;



    @Override
    public String toString(){
        Date d = new Date(mMatchDate*1000);
        return mMatchId.toString() +"played at:" + df.format(d).toString();
    }

    /**
     * Created by pengfei on 15-9-2.
     */

    //Generate random date
    //
    static class Dateutil{
        public static Date randomDate(String beginDate, String endDate){
            try{
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date start = df.parse(beginDate);
                Date end   = df.parse(endDate);
                if(start.getTime() >= end.getTime()){
                    return null;
                }
                long date = random(start.getTime(), end.getTime());
                return new Date(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return null;
        }

        private static long random(long begin, long end){
            long rtn = begin + (long)(Math.random() * (end - begin));

            if(rtn == begin || rtn == end){
                return random(begin, end);
            }

            return rtn;
        }
    }
}
