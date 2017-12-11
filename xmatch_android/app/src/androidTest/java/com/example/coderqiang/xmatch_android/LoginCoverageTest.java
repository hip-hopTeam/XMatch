package com.example.coderqiang.xmatch_android;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.example.coderqiang.xmatch_android.activity.LoginActivity;
import com.robotium.solo.Solo;

/**
 * Created by coderqiang on 2017/11/23.
 */

public class LoginCoverageTest extends ActivityInstrumentationTestCase2 {
    private Solo solo;
    private LoginActivity loginActivity;

    public LoginCoverageTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
        loginActivity= (LoginActivity) getActivity();
    }

    public void testLogin() {
        solo.clickOnView(loginActivity.findViewById(R.id.login_sign_up));
        solo.goBack();

        solo.enterText((EditText) loginActivity.findViewById(R.id.account), "");
        solo.enterText((EditText) loginActivity.findViewById(R.id.password), "");
        solo.clickOnView(loginActivity.findViewById(R.id.sign_in_button));

        solo.enterText((EditText) loginActivity.findViewById(R.id.account), "west2onlin");
        solo.enterText((EditText) loginActivity.findViewById(R.id.password), "12");
        solo.clickOnView(loginActivity.findViewById(R.id.sign_in_button));

        solo.clearEditText((EditText) loginActivity.findViewById(R.id.account));
        solo.clearEditText((EditText) loginActivity.findViewById(R.id.password));

        solo.enterText((EditText) loginActivity.findViewById(R.id.account), "west2online");
        solo.enterText((EditText) loginActivity.findViewById(R.id.password), "123123");
        solo.clickOnView(loginActivity.findViewById(R.id.sign_in_button));

        solo.clearEditText((EditText) loginActivity.findViewById(R.id.account));
        solo.clearEditText((EditText) loginActivity.findViewById(R.id.password));

        solo.enterText((EditText) loginActivity.findViewById(R.id.account), "west2online");
        solo.enterText((EditText) loginActivity.findViewById(R.id.password), "123123");
        solo.clickOnView(loginActivity.findViewById(R.id.sign_in_button));
        solo.waitForActivity("ManagerMainActivity",5000);

    }

    public void testLogin2() {
        solo.enterText((EditText) loginActivity.findViewById(R.id.account), "zhengshiqiang");
        solo.enterText((EditText) loginActivity.findViewById(R.id.password), "123123");
        solo.clickOnView(loginActivity.findViewById(R.id.sign_in_button));
        solo.waitForActivity("AddActivityActivity",5000);

    }

    public void testLogin3() {
        solo.enterText((EditText) loginActivity.findViewById(R.id.account), "zhengshiqiang");
        solo.enterText((EditText) loginActivity.findViewById(R.id.password), "123123");
        solo.sendKey(Solo.ENTER);
        solo.clickOnView(loginActivity.findViewById(R.id.sign_in_button));
        solo.waitForActivity("AddActivityActivity",5000);

    }

}
