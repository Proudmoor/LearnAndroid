package com.zpf416.dota2lastmacth;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by pengfei on 15-9-2.
 */

//Generate random date
class Dateutil{
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
public class MatchPool {
    private ArrayList<Match> mMatches;

    private static MatchPool sMatchPool;
    private Context mAppContext;

    private MatchPool(Context appContext){
        mAppContext = appContext;
        mMatches = new ArrayList<>();

        //Just for Test
        Dateutil dt = new Dateutil();
        for(int i = 0; i < 100; i++){
            Match m = new Match();
            m.setTitle("比赛时间 ：" + dt.randomDate("2015-1-1", "2015-9-1"));
//            m.setDate();
//            m.setDuration();
//            m.setFirstBloodTime();
//            m.setFirstBloodTime();
//            m.setMatchId();
//            m.setWinner();
//            m.setHeroPlayed();
            mMatches.add(m);
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
