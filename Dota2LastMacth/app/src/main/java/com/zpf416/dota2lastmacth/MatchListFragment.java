package com.zpf416.dota2lastmacth;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MatchListFragment extends Fragment {
    private ArrayList<Match> mMatches = new ArrayList<>();
    private ListView mListView;
    private static final String TAG = "MatchListFragment";
    static SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd HH:mm");
    //private MatchPool mPool = MatchPool.get(getActivity());
    MatchDetailDownloader<Match> mMatchDetailThread;
    public static final String API_KEY = "809DF5B077AC15B512C4ECFA836470B0";
    public static final String ENDPOINT_DT = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        
        new FetchMatchHistoryTask().execute();
        mMatchDetailThread = new MatchDetailDownloader<Match>(new Handler());
        mMatchDetailThread.setListener(new MatchDetailDownloader.Listener<Match>() {
            public void onMatchDetailDownloaded(Match m, JSONObject jo) {
                if (m != null) {
                    m.setMatchDetail(jo);
                    try {
                        Log.i(TAG, "duration is: " + (m.getMatchDetail()).getLong("duration"));

                    } catch (JSONException jse) {
                        Log.e(TAG, "can't get JOSNObject");
                    }
                }

            }
        });

        mMatchDetailThread.start();
        mMatchDetailThread.getLooper();
        Log.i(TAG, "Back ground thread started.");

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.list_match_history, container, false);

        mListView = (ListView) v.findViewById(R.id.list);

        mListView.setAdapter(new MatchAdapter(mMatches));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Match m = ((MatchAdapter)mListView.getAdapter()).getItem(position);
                Log.i(TAG, m.getTitle() + "was clicked");
                Intent i = new Intent(getActivity(), MatchPagerActivity.class);
                i.putExtra(MatchFragment.EXTRA_MATCH_ID, m.getId());
                startActivity(i);
            }
        });

        return v;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mMatchDetailThread.quit();
        Log.i(TAG, "Background thread destroyed");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        mMatchDetailThread.clearQueue();
    }

    private class MatchAdapter extends ArrayAdapter<Match>{
        public MatchAdapter(ArrayList<Match> matches){
            super(getActivity(), 0, matches);
        }

        @Override
        public View getView(int position, View convertView , ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.row_of_history, null);
            }

            Match m = getItem(position);
            Long matchId = m.getMatchId();
            TextView idTextView = (TextView) convertView.findViewById(R.id.match_id);
            idTextView.setText(matchId.toString());

            JSONArray players = m.getPlayers();
            int playerSlot = 0;
            int heroPlayed = 0;

            try{
                for(int i = 0; i < players.length(); i++){
                    JSONObject player = players.getJSONObject(i);
                    if(player.getLong("account_id") == 138383743) {
                        heroPlayed = player.getInt("hero_id");
                        playerSlot = player.getInt("player_slot");
                        break;
                    }
                }
            }catch (JSONException jse){
                Log.e(TAG, "Get Player failed.", jse);
            }

            TextView dateTextView = (TextView) convertView.findViewById(R.id.match_date);
            dateTextView.setText(df.format(new Date(m.getMatchDate())));

            String path = heroPlayed +".png";
           // AssetManager manager = getActivity().getAssets();
            Bitmap bp = getBitmapFromAsset(getActivity(), path);
            ImageView heroView = (ImageView)convertView.findViewById(R.id.hero_id_image);
            heroView.setImageBitmap(bp);
            //construct the uri of matchDetailJSON

            String url = Uri.parse(ENDPOINT_DT).buildUpon()
                    .appendQueryParameter("key", API_KEY)
                    .appendQueryParameter("match_id", matchId.toString())
                    .build().toString();
            mMatchDetailThread.queueMatchDetail(m, url);
            //m.setMatchDetail(jo);
            JSONObject jo = m.getMatchDetail();
            if(jo !=null){
                try{
                    boolean radiantWin = jo.getBoolean("radiant_win");

                    TextView winTextView = (TextView) convertView.findViewById(R.id.win_lose);
                    if(radiantWin && playerSlot < 5||!radiantWin &&playerSlot >5) {
                        winTextView.setTextColor(Color.GREEN);
                        winTextView.setText("win");
                    } else {
                        winTextView.setTextColor(Color.RED);
                        winTextView.setText("lose");
                    }
                }catch(JSONException jse){
                    Log.e(TAG,"Can't get radiantWin.");
                }
            }


            return convertView;
        }
    }


    private class FetchMatchHistoryTask extends AsyncTask<Void, Void, ArrayList<Match> >{
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading, 请稍等");
            dialog.setTitle("连接服务器");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected ArrayList<Match> doInBackground(Void ... params){
            return new MatchFetchr().fetchMatchHistory("138383743");
        }

        @Override
        protected void onPostExecute(ArrayList<Match> matches){
            mMatches = matches;
            mListView.setAdapter(new MatchAdapter(mMatches));
            dialog.cancel();
            for(Match m : mMatches){
                MatchPool.get(getActivity()).addMatch(m);
            }
        }
    }

    public static  Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            Log.d(TAG, "Cant' open file" + filePath);
        }

        return bitmap;
    }
}
