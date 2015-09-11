package com.zpf416.dota2lastmacth;


import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


/**
 * A placeholder fragment containing a simple view.
 */
public class MatchFragment extends Fragment {
    private static final String TAG = "MatchFragment";
    public static final String EXTRA_MATCH_ID = "com.zpf416.matchintent.matchId";
    private Match mMatch;
    private ArrayList<Player> mPlayers = new ArrayList<>();

    public static MatchFragment newInstance(UUID matchId){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_MATCH_ID, matchId);

        MatchFragment fragment = new MatchFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       // mMatch = new Match();
        UUID matchId = (UUID) getArguments()
                .getSerializable(EXTRA_MATCH_ID);

        Match m = MatchPool.get(getActivity()).getMatch(matchId);
        if(m ==null)
            Toast.makeText(getActivity(),"Wait a moment, Downlading!",Toast.LENGTH_SHORT).show();
        else
            mMatch = m;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_match, container, false);


        JSONArray ja = mMatch.getPlayers();

        try{
            for( int i = 0; i < 10; i++) {
                JSONObject jo = ja.getJSONObject(i);
                Player pa = new Player();
                pa.setAccountId(jo.getLong("account_id"));
                pa.setHeroPlayed(jo.getInt("hero_id"));
                mPlayers.add(pa);
            }
        }catch (JSONException jse){
            Log.e(TAG,"Parse JOSNPlayers failed");
        }

        ListView list = (ListView) v.findViewById(R.id.detail_listview);
        list.setAdapter(new PlayerAdapter(mPlayers));
        return v;

    }

    private class PlayerAdapter extends ArrayAdapter<Player>{
        public PlayerAdapter(ArrayList<Player> players){
            super(getActivity(),0,players);
        }

        @Override
        public View getView(int position, View convertView , ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.row_of_match_detail, null);
            }

            Player p = getItem(position);

            TextView avatarTextView = (TextView) convertView.findViewById(R.id.player_nickname);
            avatarTextView.setText(p.getAccountId() + "");

//            TextView idTextView = (TextView) convertView.findViewById(R.id.player_avatar);
//            idTextView.setText(p.getAccountId() + "");

//            TextView dateTextView = (TextView) convertView.findViewById(R.id.match_date);
//            dateTextView.setText(df.format(new Date(m.getMatchDate())));
            int i = p.getHeroPlayed();
            String path = i+".png";
            // AssetManager manager = getActivity().getAssets();
           try{
               Bitmap bp = BitmapFactory.decodeStream(getActivity().getAssets().open(path));
               ImageView heroView = (ImageView)convertView.findViewById(R.id.detail_hero_image);
               heroView.setImageBitmap(bp);
           } catch(IOException ioe){
               Log.e(TAG, "Can't open file");
           }


            return convertView;
        }
    }

//    private class FetchDetailTask extends AsyncTask<String, Void, JSONArray> {
//        @Override
//        protected JSONArray doInBackground(String... params){
//            return new MatchFetchr().parseDetail(params);
//        }
//
//        @Override
//        protected void onPostExecute(JSONArray players){
//            mPlayers = players;
//
//        }
//    }en
}


