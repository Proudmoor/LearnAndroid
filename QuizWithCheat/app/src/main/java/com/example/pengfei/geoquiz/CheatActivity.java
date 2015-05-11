package com.example.pengfei.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by pengfei on 15-5-11.
 */
public class CheatActivity extends Activity {

    private static final String TAG="CheatActivity";
    public static String EXTRA_ANSWER_IS_TRUE =
            "com.example.pengfei.geoquiz.answer_is_true";
    public static String EXTRA_ANSWER_IS_SHOWN =
            "come.exapmel.pengfei.geoquiz.answer_is_shown";



    private boolean mAnswerIsTrue;

    private TextView mAnswerTextView;
    private Button mShowAnswer;

    private void setAnswerShowResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate() called");
        setContentView(R.layout.activity_cheat);

        setAnswerShowResult(false);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = (TextView)findViewById(R.id.answerTextView);

        mShowAnswer = (Button)findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAnswerIsTrue){
                    mAnswerTextView.setText(R.string.true_button);
                } else{
                    mAnswerTextView.setText(R.string.false_button);
                }
                setAnswerShowResult(true);
            }
        });
    }
}
