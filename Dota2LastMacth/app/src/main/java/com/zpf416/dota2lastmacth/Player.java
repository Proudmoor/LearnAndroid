package com.zpf416.dota2lastmacth;

/**
 * Created by zpff on 2015/9/10.
 */
public class Player {
    public long getAccountId() {
        return mAccountId;
    }

    public void setAccountId(long accountId) {
        mAccountId = accountId;
    }

    public int getHeroPlayed() {
        return mHeroPlayed;
    }

    public void setHeroPlayed(int heroPlayed) {
        mHeroPlayed = heroPlayed;
    }

    public int getKill() {
        return mKill;
    }

    public void setKill(int kill) {
        mKill = kill;
    }

    public int getSlayed() {
        return mSlayed;
    }

    public void setSlayed(int slayed) {
        mSlayed = slayed;
    }

    public int getAssist() {
        return mAssist;
    }

    public void setAssist(int assist) {
        mAssist = assist;
    }

    private long mAccountId;
    private int  mHeroPlayed;
    private int  mKill;
    private int  mSlayed;
    private int  mAssist;
}
