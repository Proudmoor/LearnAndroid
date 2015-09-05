package com.zpf416.dota2lastmacth;


import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;


/**
 * A placeholder fragment containing a simple view.
 */
public class MatchFragment extends Fragment {
    public static final String EXTRA_MATCH_ID = "com.zpf416.matchintent.matchId";
    private Match mMatch;
    private EditText mTitleField;

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

        mMatch = MatchPool.get(getActivity()).getMatch(matchId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_match, container, false);

        mTitleField = (EditText) v.findViewById(R.id.change_title);
        //mTitleField.setText("这场比赛 "+mMatch.getWinner()+" 胜利了");
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mMatch.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                mMatch.getTitle();
            }
        });

        return v;
    }
}
