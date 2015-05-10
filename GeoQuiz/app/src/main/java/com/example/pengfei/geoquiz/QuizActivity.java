package com.example.pengfei.geoquiz;

import android.os.Bundle;
import android.view.Menu;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends Activity {
    //注册四个按钮和一个文本框
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPrevButton;
    private TextView mQuestionTextview;
    //用数组的方式存放四个问题的资源
    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_1,false),
            new TrueFalse(R.string.question_2,false),
            new TrueFalse(R.string.question_3,false),
            new TrueFalse(R.string.question_4,false)
    };
//问题的索引
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //初始化文本框文字
        mQuestionTextview = (TextView)findViewById(R.id.question_text_view);
        updateQuestion();

        //选择正确按钮并注册监听按钮
        mTrueButton = (Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // nothing yet
                checkAnswer(true);
            }

        });
        //选择错误按钮。。。。
        mFalseButton= (Button)findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                //nothing yet
                checkAnswer(false);
            }
        });
        //下一题按钮，触发时文本框显示下一题
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
        public void onClick(View v){
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        //上一题按钮，触发时文本框显示上一题
        mPrevButton = (Button) findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCurrentIndex = (mCurrentIndex>0? mCurrentIndex-1:mCurrentIndex+3) % mQuestionBank.length  ;
                updateQuestion();
            }
        });
    }
//    更新文本框显示的问题
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextview.setText(question);
    }

//    判断问题对错的方法
    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int messageResId = 0;

        if(userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

}
