package com.example.pengfei.spotandreport;

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
    }
}
