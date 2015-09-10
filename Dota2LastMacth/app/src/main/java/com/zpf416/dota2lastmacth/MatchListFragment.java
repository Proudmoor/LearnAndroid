package com.zpf416.dota2lastmacth;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MatchListFragment extends Fragment {
    private ArrayList<Match> mMatches = new ArrayList<>();
    private ListView mListView;
    private static final String TAG = "MatchListFragment";
    static SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd，HH:mm");
    //private MatchPool mPool = MatchPool.get(getActivity());

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //getActivity().setTitle(R.string.title_activity_match_list);
       // mMatches = new ArrayList<Match>();
        setRetainInstance(true);



       // mMatches = MatchPool.get(getActivity()).getMatches();

//        ArrayAdapter<Match> adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1,
//                mMatches);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.list_match_history, container, false);

        mListView = (ListView) v.findViewById(R.id.list);
        new FetchMatchHistoryTask().execute();
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

            TextView idTextView = (TextView) convertView.findViewById(R.id.match_id);
            idTextView.setText(m.getMatchId().toString());

            TextView dateTextView = (TextView) convertView.findViewById(R.id.match_date);
            dateTextView.setText(df.format(new Date(m.getMatchDate())));
            int i = m.getHeroPlayed();
            String path = i+".png";
           // AssetManager manager = getActivity().getAssets();
            Bitmap bp = getBitmapFromAsset(getActivity(), path);
            ImageView heroView = (ImageView)convertView.findViewById(R.id.hero_id_image);
            heroView.setImageBitmap(bp);

            return convertView;
        }
    }


    private class FetchMatchHistoryTask extends AsyncTask<Void, Void, ArrayList<Match> >{
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }
        @Override
        protected ArrayList<Match> doInBackground(Void ... params){
            return new MatchFetchr().fetchMatchHistory("76561198098649471");
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
