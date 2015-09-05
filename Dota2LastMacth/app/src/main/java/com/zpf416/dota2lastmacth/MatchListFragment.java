package com.zpf416.dota2lastmacth;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class MatchListFragment extends ListFragment{
    private ArrayList<Match> mMatches;
    private static final String TAG = "MatchListFragment";
    static SimpleDateFormat df = new SimpleDateFormat("yy/MM/dd，HH:mm");
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.title_activity_match_list);

        mMatches = MatchPool.get(getActivity()).getMatches();

//        ArrayAdapter<Match> adapter = new ArrayAdapter<>(getActivity(),
//                android.R.layout.simple_list_item_1,
//                mMatches);
        MatchAdapter adapter = new MatchAdapter(mMatches);
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        Match m = ((MatchAdapter)getListAdapter()).getItem(position);
        Log.i(TAG, m.getTitle() + "was clicked");
        //Changed to use PagerAdapter
//        Intent i = new Intent(getActivity(), MatchActivity.class);
        Intent i = new Intent(getActivity(), MatchPagerActivity.class);
        i.putExtra(MatchFragment.EXTRA_MATCH_ID, m.getId());
        startActivity(i);
    }

    private class MatchAdapter extends ArrayAdapter<Match>{
        public MatchAdapter(ArrayList<Match> matches){
            super(getActivity(), 0, matches);
        }

        @Override
        public View getView(int position, View convertView , ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_match, null);
            }

            Match m = getItem(position);

            TextView idTextView = (TextView) convertView.findViewById(R.id.match_id);
            idTextView.setText(m.getMatchId());
            TextView dateTextView = (TextView) convertView.findViewById(R.id.match_date);
            dateTextView.setText(df.format(m.getDate()));
            TextView timeTextView = (TextView) convertView.findViewById(R.id.match_time);
            timeTextView.setText(m.getDuration());
//            TextView heroTextView = (TextView) convertView.findViewById(R.id.match_played_hero);
//            heroTextView.setText(m.getHeroPlayed());
            TextView winTextView = (TextView) convertView.findViewById(R.id.match_winner);
            winTextView.setText(m.getWinner());


            return convertView;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        ((MatchAdapter)getListAdapter()).notifyDataSetChanged();
    }
}