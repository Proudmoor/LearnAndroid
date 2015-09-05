package com.zpf416.dota2lastmacth;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zpff on 2015/9/5.
 */
public class Photo {
    private static final String JSON_FILENAME = "filename";

    private String mFileName;

    public Photo(String fileName){
        mFileName = fileName;
    }

    public Photo(JSONObject json) throws JSONException{
        mFileName = json.getString(JSON_FILENAME);
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put(JSON_FILENAME, mFileName);
        return json;
    }

    public String getFileName(){
        return mFileName;
    }
}
