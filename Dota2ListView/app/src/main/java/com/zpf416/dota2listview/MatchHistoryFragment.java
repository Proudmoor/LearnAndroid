package com.zpf416.dota2listview;

import android.app.ListFragment;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class MatchHistoryFragment extends ListFragment {
    ListView mListView;
    private ArrayList<String> mHistoryJSONString;
    private static final String TAG = "MatchHistoryFragment";

    @Override
    public void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);

        setRetainInstance(true);
        new FetchMatchTask().execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_match_history, container, false);
        mListView = (ListView) v.findViewById(R.id.listView);
        setListAdapter(new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, mHistoryJSONString));
        return v;
    }

    private class FetchMatchTask extends AsyncTask<Void, Void, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            return new MatchFetchr().fetchMatchHistory("76561198098649471");
        }

        @Override
        protected void onPostExecute(ArrayList<String> matches) {
            mHistoryJSONString = matches;
        }
    }
}


