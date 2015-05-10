package com.example.pengfei.geoquiz;

import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.view.View;
import android.widget.Button;


public class Quiz extends Activity {

    private Button mTrueButton;
    private Button mFalseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v){
                // nothing yet
            }

        });

        mFalseButton= (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                //nothing yet
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

}
