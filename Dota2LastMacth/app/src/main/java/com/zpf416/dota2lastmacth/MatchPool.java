package com.zpf416.dota2lastmacth;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

public class MatchPool {
    private ArrayList<Match> mMatches;

    private static MatchPool sMatchPool;
    private Context mAppContext;

    private MatchPool(Context appContext){
        mAppContext = appContext;
        mMatches = new ArrayList<>();
    }

    public static MatchPool get(Context c){
        if(sMatchPool == null){
            sMatchPool = new MatchPool(c.getApplicationContext());
        }
        return sMatchPool;
    }
    public void addMatch(Match m){
        mMatches.add(m);
    }

    public ArrayList<Match> getMatches(){
        return mMatches;
    }

    public Match getMatch(UUID id){
        for(Match m : mMatches){
            if(m.getId().equals(id))
                return m;
        }
        return null;
    }
}
