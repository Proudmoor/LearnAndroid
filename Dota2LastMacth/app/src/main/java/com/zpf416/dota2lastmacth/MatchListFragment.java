package com.zpf416.dota2lastmacth;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MatchListFragment extends ListFragment{
    private ArrayList<Match> mMatches;
    private static final String TAG = "MatchListFragment";

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
        Log.d(TAG, m.getTitle() + "was clicked");
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
            dateTextView.setText(m.getDate().toString());
            TextView timeTextView = (TextView) convertView.findViewById(R.id.match_time);
            timeTextView.setText(m.getDuration());
            TextView heroTextView = (TextView) convertView.findViewById(R.id.match_played_hero);
            heroTextView.setText(m.getHeroPlayed());
            TextView winTextView = (TextView) convertView.findViewById(R.id.match_winner);
            winTextView.setText(m.getWinner());


            return convertView;
        }
    }
}