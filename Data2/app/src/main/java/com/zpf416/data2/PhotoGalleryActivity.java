package com.zpf416.data2;


import android.support.v4.app.Fragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {


    @Override
    public Fragment createFragment(){
        return new PhotoGalleryActivity();
    }
}
