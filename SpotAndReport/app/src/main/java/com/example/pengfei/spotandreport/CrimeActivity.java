package com.example.pengfei.spotandreport;

import android.support.v4.app.Fragment;

import java.util.UUID;


public class CrimeActivity extends SingleFragmentActivity{

//    @Override  /** 没有抽象类之前 建立具体的Fragment，现在使用抽象类。
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//        //获取Fragmentmanager 来管理Fragment
//        FragmentManager fm = getFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
//
//        if(fragment == null){
//            fragment  = new CrimeFragment();
//            fm.beginTransaction()
//                    .add(R.id.fragmentContainer, fragment)
//                    .commit();
//        }
//    }

    @Override
    protected Fragment createFragment(){
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
//        return new CrimeFragment();  原来通用的从Activity创建Fragment的返回
        //现在创建...
        return CrimeFragment.newInstance(crimeId);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_crime, menu);
//        return true;
//    } //暂时不需要

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
