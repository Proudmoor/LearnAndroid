package com.zpf416.dota2lastmacth;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * Created by zpff on 2015/9/2.
 */
public class Match {

    private String mTitle;
    private UUID mId;

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



    public Match(){
        mId = UUID.randomUUID();
        mDate = new Date();
        //Just for test
        Random rd = new Random();
        mMatchId = rd.nextInt(1000000000)+"";
        mWinner = (rd.nextInt(100) % 2 ==  0 ? "天辉" : "夜魇");
        mHeroPlayed = rd.nextInt(110) + "";

    }

    @Override
    public String toString(){
        return mTitle;
    }
}
