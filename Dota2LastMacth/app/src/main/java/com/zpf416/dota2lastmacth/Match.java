package com.zpf416.dota2lastmacth;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by zpff on 2015/9/2.
 */
public class Match {
    private static final String JSON_PHOTO = "photo";

    private String mTitle;
    private UUID mId;

    public Photo getPhoto() {
        return mPhoto;
    }

    public void setPhoto(Photo photo) {
        mPhoto = photo;
    }

    private Photo mPhoto;

    public void setId(UUID id) {
        mId = id;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public void setMatchId(String matchId) {
        mMatchId = matchId;
    }

    public void setWinner(String winner) {
        mWinner = winner;
    }

    public void setDuration(String duration) {
        mDuration = duration;
    }

    public void setHeroPlayed(String heroPlayed) {
        mHeroPlayed = heroPlayed;
    }

    public void setFirstBloodTime(String firstBloodTime) {
        mFirstBloodTime = firstBloodTime;
    }

    private Date mDate;

    public String getMatchId() {
        return mMatchId;
    }

    public String getWinner() {
        return mWinner;
    }

    public String getDuration() {
        return mDuration;
    }

    public String getHeroPlayed() {
        return mHeroPlayed;
    }

    public String getFirstBloodTime() {
        return mFirstBloodTime;
    }

    public Date getDate() {
        return mDate;
    }

    private String mMatchId;
    private String mWinner;
    private String mDuration;
    private String mHeroPlayed;
    private String mFirstBloodTime;
    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }


    SimpleDateFormat df = new SimpleDateFormat("yy年,MM月dd日，HH:mm");
    Dateutil du = new Dateutil();
    public Match() throws JSONException{
        mId = UUID.randomUUID();
        mDate = Dateutil.randomDate("2015-1-1", "2015-9-1");
        //Just for test
        Random rd = new Random();
        mMatchId = rd.nextInt(1000000000)+"";
        mWinner = (rd.nextInt(100) % 2 ==  0 ? "天辉" : "夜魇");
        mHeroPlayed = rd.nextInt(110) + "";
//        if(json.has(JSON_PHOTO))
//            mPhoto = new Photo(json.getJSONObject(JSON_PHOTO));

    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();

        json.put(JSON_PHOTO, mPhoto.toJSON());
        return json;
    }

    @Override
    public String toString(){
        return mTitle;
    }

    /**
     * Created by pengfei on 15-9-2.
     */

    //Generate random date
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
