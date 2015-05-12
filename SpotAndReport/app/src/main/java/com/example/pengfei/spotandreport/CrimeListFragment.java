package com.example.pengfei.spotandreport;

import android.app.ListFragment;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pengfei on 15-5-12.
 */
public class CrimeListFragment extends ListFragment {
    private ArrayList<Crime> mCrimes;
    private static final String TAG = "CrimeListFragment";

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //ListFragment创建时，修改Title 获取事件池里的事件。
        getActivity().setTitle(R.string.crimes_title);
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

//        ArrayAdapter<Crime> adapter =
//                new ArrayAdapter<Crime>(getActivity(),
//                        android.R.layout.simple_list_item_1,
//                        mCrimes);//原来使用安卓里给的的视图
    //Adapter is cursor的作用，可以用来指向List中的位置之类
        CrimeAdapter adapter = new CrimeAdapter(mCrimes);
        setListAdapter(adapter);

    }

    @Override//重写原来
    public void onListItemClick(ListView l, View v, int positon, long id){
        Crime c = (Crime) (getListAdapter()).getItem(positon);
        Log.i(TAG, c.getTitle() + "was Clicked"); //用来在logcat中显示 点击 事件触发。
    }

    private class CrimeAdapter extends ArrayAdapter<Crime> {

        public CrimeAdapter(ArrayList<Crime> crimes){
            super(getActivity(), 0, crimes);
        }

        @Override//重写getView 用来定制View的显示方式。
        public View getView(int position, View convertView, ViewGroup parent){
            if(convertView == null){
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_crime,null);
            }

            Crime c = getItem(position);

            TextView titleTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView dateTextView =
                    (TextView) convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(DateFormat.format("yyyy年,MMMM dd,hh:mm",c.getDate()));

            CheckBox solvedCheckBox =
                    (CheckBox) convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }


    }
}
