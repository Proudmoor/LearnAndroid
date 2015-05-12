package com.example.pengfei.spotandreport;

import android.widget.Button;
import android.widget.CheckBox;

import java.util.Date;
import java.util.UUID;

/**
 * Created by pengfei on 15-5-12.
 */
public class Crime {
    public UUID getId() {
        return mId;
    }

    private UUID mId;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    private String mTitle;

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    private Date mDate;
    private boolean mSolved;


}
