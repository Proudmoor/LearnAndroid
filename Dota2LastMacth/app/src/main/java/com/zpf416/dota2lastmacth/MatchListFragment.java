package com.zpf416.dota2lastmacth;

import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MatchListFragment extends Fragment {
    private ArrayList<Match> mMatches;
    private static final String TAG = "MatchListFragment";
    static SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd，HH:mm");
    GridView mGridView;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //getActivity().setTitle(R.string.title_activity_match_list);
        setRetainInstance(true);

        new FetchMatchHistoryTask().execute();

       // mMatches = MatchPool.get(getActivity()).getMatches();

//        ArrayAdapter<Match> adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1,
//                mMatches);
//        MatchAdapter adapter = new MatchAdapter(mMatches);
//        setListAdapter(adapter);

    }
    void setupAdapter(){
        if(getActivity() == null || mGridView == null) return;

        if(mMatches != null){
            mGridView.setAdapter(new ArrayAdapter<Match>(getActivity(),
                    android.R.layout.simple_gallery_item, mMatches));
        } else{
            mGridView.setAdapter(null);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.list_detail_match, container, false);

        mGridView = (GridView) v.findViewById(R.id.gridView);

        setupAdapter();

        return v;
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id){
//        Match m = ((MatchAdapter)getListAdapter()).getItem(position);
//        Log.i(TAG, m.getTitle() + "was clicked");
//        //Changed to use PagerAdapter
////        Intent i = new Intent(getActivity(), MatchActivity.class);
//        Intent i = new Intent(getActivity(), MatchPagerActivity.class);
//        i.putExtra(MatchFragment.EXTRA_MATCH_ID, m.getId());
//        startActivity(i);
//    }

//    private class MatchAdapter extends ArrayAdapter<Match>{
//        public MatchAdapter(ArrayList<Match> matches){
//            super(getActivity(), 0, matches);
//        }
//
//        @Override
//        public View getView(int position, View convertView , ViewGroup parent){
//            if(convertView == null){
//                convertView = getActivity().getLayoutInflater()
//                        .inflate(R.layout.list_item_match, null);
//            }
//
//            Match m = getItem(position);
//
//            TextView idTextView = (TextView) convertView.findViewById(R.id.match_id);
//            idTextView.setText(m.getMatchId().toString());
//            Date date = new Date(m.getMatchDate());
//            TextView dateTextView = (TextView) convertView.findViewById(R.id.match_date);
//            dateTextView.setText(df.format(m));
//
//
//            TextView heroTextView = (TextView) convertView.findViewById(R.id.hero_id);
//            heroTextView.setText(m.getHeroPlayed());
//
//
//            return convertView;
//        }
//    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        ((MatchAdapter)getListAdapter()).notifyDataSetChanged();
//    }

    private class FetchMatchHistoryTask extends AsyncTask<Void, Void, ArrayList<Match> >{
        @Override
        protected ArrayList<Match> doInBackground(Void ... params){
            return new MatchFetchr().fetchMatchHistory("76561198098649471");
        }

        @Override
        protected void onPostExecute(ArrayList<Match> matches){
            mMatches = matches;
            setupAdapter();
        }
    }
}