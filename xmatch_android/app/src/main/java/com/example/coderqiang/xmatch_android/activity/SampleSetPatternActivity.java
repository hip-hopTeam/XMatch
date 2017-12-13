package com.example.coderqiang.xmatch_android.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import me.zhanghai.android.patternlock.PatternUtils;
import me.zhanghai.android.patternlock.PatternView;
import me.zhanghai.android.patternlock.SetPatternActivity;

/**
 * Created by coderqiang on 2017/12/14.
 */

public class SampleSetPatternActivity extends SetPatternActivity {

    private static final String TAG="SampleSetPattern";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.mMessageText.setText("输入手势密码");
        mLeftButton.setVisibility(View.GONE);
        mRightButton.setVisibility(View.GONE);
    }


    @Override
    protected void onSetPattern(List<PatternView.Cell> pattern) {
        String patternSha1 = PatternUtils.patternToSha1String(pattern);
        Log.i(TAG, "onSetPattern: "+patternSha1);
    }

    @Override
    public void onPatternDetected(List<PatternView.Cell> newPattern) {
        super.onPatternDetected(newPattern);
        String pass = "";
        for (int i = 0; i < newPattern.size(); i++) {
            pass+=newPattern.get(i).getRow()*3+newPattern.get(i).getColumn()+1+"";
        }
        Log.i(TAG, "onPatternCellAdded: pass"+pass);


    }
}
