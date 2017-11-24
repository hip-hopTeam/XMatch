package com.example.coderqiang.xmatch_android;

import android.test.ActivityInstrumentationTestCase2;

import com.example.coderqiang.xmatch_android.activity.LoginActivity;
import com.example.coderqiang.xmatch_android.activity.SignUpActivity;
import com.robotium.solo.Solo;

/**
 * Created by coderqiang on 2017/11/23.
 */

public class SignUpTest extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    private SignUpActivity signUpActivity;

    public SignUpTest() {
        super(SignUpActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        signUpActivity = (SignUpActivity) getActivity();
    }

    private void testSingn() {

    }
}
