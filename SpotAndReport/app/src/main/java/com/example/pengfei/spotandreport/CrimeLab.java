package com.example.pengfei.spotandreport;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by pengfei on 15-5-12.
 */
public class CrimeLab {
    private ArrayList<Crime> mCrimes;

    private Context mAppContext;
    private static CrimeLab sCrimeLab;

    public static CrimeLab get(Context c) {
        if(sCrimeLab == null){
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        //初始化100个事件。
        for(int i=0; i < 100; i++){
            Crime c = new Crime();
            c.setTitle("事件 #" +i);
            c.setSolved(i % 2 == 0);
            mCrimes.add(c);
        }
    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for(Crime c: mCrimes){
            if(c.getId().equals(id))
                return c;
        }
        return null;
    }
}
