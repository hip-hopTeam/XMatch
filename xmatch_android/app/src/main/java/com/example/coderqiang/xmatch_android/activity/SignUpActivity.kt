package com.example.coderqiang.xmatch_android.activity

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.provider.ContactsContract
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.TranslateAnimation
import android.view.inputmethod.EditorInfo
import android.widget.*
import butterknife.BindView
import com.example.coderqiang.xmatch_android.R
import com.example.coderqiang.xmatch_android.api.DepManagerApi
import com.example.coderqiang.xmatch_android.api.Login
import com.example.coderqiang.xmatch_android.dao.DBManager
import com.example.coderqiang.xmatch_android.model.DepManager
import com.example.coderqiang.xmatch_android.model.User
import com.example.coderqiang.xmatch_android.util.DefaultConfig
import com.example.coderqiang.xmatch_android.util.DepManagerLab
import com.example.coderqiang.xmatch_android.util.RegexUtil
import com.example.coderqiang.xmatch_android.util.ResultCode
import com.wang.avi.AVLoadingIndicatorView
import kotlinx.android.synthetic.main.activity_sign_up.*
import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by coderqiang on 2017/11/20.
 */

class SignUpActivity : AppCompatActivity(){
    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    private var mAccountView: AutoCompleteTextView? = null
    private var mPasswordView: EditText? = null
    private var mLoginFormView: View? = null
    private var mRem_passwords: Switch? = null
    var depNameTv:EditText? = null
    var depPhoneTv:EditText? = null
    var depEmailTv:EditText? = null
    var depSummaryTv:EditText? = null
    private var pref: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    companion object {

        /**
         * Id to identity READ_CONTACTS permission request.
         */
        private val REQUEST_READ_CONTACTS = 0
        private val TAG = "LoginActivity_1"
    }
    //从Logincheck.asp获取的id和num

    private val id_2: String? = null//从LOGIN_CHK_XS获取,后面获取web信息的唯一标识码

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_sign_up)
        //沉浸状态栏
        setLayout()
        // Set up the login form.
        mAccountView = findViewById<View>(R.id.account) as AutoCompleteTextView
        depNameTv = findViewById<View>(R.id.sign_up_name) as EditText
        depPhoneTv = findViewById<View>(R.id.sign_up_phone) as EditText
        depEmailTv = findViewById<View>(R.id.sign_up_email) as EditText
        depSummaryTv = findViewById<View>(R.id.sign_up_summary) as EditText
        populateAutoComplete()
        mPasswordView = findViewById<View>(R.id.password) as EditText
        val signUpButton = findViewById<View>(R.id.sign_up_button) as Button
        signUpButton.setOnClickListener { signUp() }
        mLoginFormView = findViewById(R.id.login_form)
    }

    private fun setLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }
    }

    private fun populateAutoComplete() {
        if (!mayRequestContacts()) {
            return
        }
    }

    private fun mayRequestContacts(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
            Snackbar.make(mAccountView!!, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok) { requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_READ_CONTACTS) }
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_READ_CONTACTS)
        }
        return false
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete()
            }
        }
    }

    fun signUp(){
        if (RegexUtil.isEmpty(account.text.toString())) {
            RegexUtil.showToast(this, "账号不能为空!")
            return
        }
        if (RegexUtil.isEmpty(password.text.toString())) {
            RegexUtil.showToast(this, "密码不能为空!")
            return
        }
        if (password.text.toString().length <= 6) {
            RegexUtil.showToast(this, "密码长度必须大于6!")
            return
        }
        if (RegexUtil.isEmpty(sign_up_name.text.toString())) {
            RegexUtil.showToast(this, "名字不能为空!")
            return
        }
        if (!RegexUtil.isPhone(sign_up_phone.text.toString())) {
            RegexUtil.showToast(this, "手机格式不正确!")
            return
        }
        if (!RegexUtil.isPhone(sign_up_email.text.toString())) {
            RegexUtil.showToast(this, "邮箱格式不正确!")
            return
        }
        Observable.create(Observable.OnSubscribe<Int> { subscriber ->
            val depManager = DepManager()
            depManager.password= password.text.toString()
            depManager.depManagerAccount = account.text.toString()
            depManager.managerName=sign_up_name.text.toString()
            depManager.phoneNum=sign_up_phone.text.toString()
            depManager.managerSummary=sign_up_summary.text.toString()
            depManager.email=sign_up_email.text.toString()
            try {
                var baseMessage = DepManagerApi.signUpManager(depManager)
                subscriber.onNext(baseMessage.code)
            } catch (e: Exception) {
                subscriber.onError(e)
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Int> {
            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "注册失败,请检查网络连接", Toast.LENGTH_SHORT).show()
            }

            override fun onNext(loginResponse: Int?) {
                when (loginResponse) {
                    1 -> {
                        Toast.makeText(applicationContext,"注册成功", Toast.LENGTH_SHORT).show()
                        onBackPressed()
                        finish()
                        return
                    }
                    31->{
                        Toast.makeText(applicationContext, "用户名已存在，请更改用户名重试", Toast.LENGTH_SHORT).show()
                    }
                    else -> Toast.makeText(applicationContext, ResultCode.map[loginResponse], Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

}
