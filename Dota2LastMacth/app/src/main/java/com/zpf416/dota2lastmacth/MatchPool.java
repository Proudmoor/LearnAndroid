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

        //Just for Test
        Match.Dateutil dt = new Match.Dateutil();
        for(int i = 0; i < 100; i++){
            try {
                Match m = new Match();
                m.setTitle("比赛时间 ：" + dt.randomDate("2015-1-1", "2015-9-1"));
                m.setDate(dt.randomDate("2015-1-1", "2015-9-1"));
//            m.setDuration();
//            m.setFirstBloodTime();
//            m.setFirstBloodTime();
//            m.setMatchId();
//            m.setWinner();
//            m.setHeroPlayed();
                mMatches.add(m);
            } catch(Exception e){
                e.printStackTrace();
            }

        }
    }

    public static MatchPool get(Context c){
        if(sMatchPool == null){
            sMatchPool = new MatchPool(c.getApplicationContext());
        }
        return sMatchPool;
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
