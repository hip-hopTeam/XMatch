package com.example.coderqiang.xmatch_android.activity

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
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

import com.example.coderqiang.xmatch_android.R
import com.example.coderqiang.xmatch_android.model.User
import com.example.coderqiang.xmatch_android.util.DefaultConfig
import com.example.coderqiang.xmatch_android.util.ResultCode
import com.wang.avi.AVLoadingIndicatorView

import rx.Observable
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

import android.Manifest.permission.READ_CONTACTS
import com.example.coderqiang.xmatch_android.api.DepManagerApi
import com.example.coderqiang.xmatch_android.api.Login
import com.example.coderqiang.xmatch_android.dao.DBManager
import com.example.coderqiang.xmatch_android.model.DepManager
import com.example.coderqiang.xmatch_android.util.DepManagerLab

/**
 * Created by coderqiang on 2017/11/9.
 */

class LoginActivity : AppCompatActivity() {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // UI references.
    var mAccountView: AutoCompleteTextView? = null
    var mPasswordView: EditText? = null
    private var mLoginFormView: View? = null
    private var mRem_passwords: Switch? = null
    private var loadingView: AVLoadingIndicatorView? = null
    var signUpBtn:TextView?=null

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
        setContentView(R.layout.activity_login)
        //沉浸状态栏
        setLayout()
        // Set up the login form.
        mAccountView = findViewById<View>(R.id.account) as AutoCompleteTextView
        populateAutoComplete()
        loadingView = findViewById<View>(R.id.login_loading) as AVLoadingIndicatorView
        mPasswordView = findViewById<View>(R.id.password) as EditText
        signUpBtn=findViewById<View>(R.id.login_sign_up) as TextView
        signUpBtn!!.setOnClickListener{signUp()}
        mPasswordView!!.setOnEditorActionListener(TextView.OnEditorActionListener { textView, id, keyEvent ->
            Log.i(TAG, "onEditorAction: Id:" + id)
            if (id == EditorInfo.IME_NULL || id == 6) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })
        val signInButton = findViewById<View>(R.id.sign_in_button) as Button
        signInButton.setOnClickListener { attemptLogin() }

        mLoginFormView = findViewById(R.id.login_form)
        mRem_passwords = findViewById<View>(R.id.rem_passwords) as Switch
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
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mAccountView!!, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok) { requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS) }
        } else {
            requestPermissions(arrayOf(READ_CONTACTS), REQUEST_READ_CONTACTS)
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

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {

        // Reset errors.
        mAccountView!!.error = null
        mPasswordView!!.error = null

        // Store values at the time of the login attempt.
        val account = mAccountView!!.text.toString()
        val password = mPasswordView!!.text.toString()

//        val account = "west2online"
//        val password="123123"

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView!!.error = getString(R.string.error_invalid_password)
            focusView = mPasswordView
            cancel = true
        }

        // Check for a valid account address.
        if (TextUtils.isEmpty(account)) {
            mAccountView!!.error = getString(R.string.error_field_required)
            focusView = mAccountView
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView!!.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            loginDepManager(account, password)
        }
    }

    fun signUp(){
        var intent=Intent(this@LoginActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun isPasswordValid(password: String): Boolean {
        //TODO: Replace this with your own logic
        return password.length > 4
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private fun showProgress(show: Boolean) {
        if (show) {
            val translateAnimation = TranslateAnimation(0f, (-mLoginFormView!!.right).toFloat(), 0f, 0f)
            translateAnimation.fillAfter = true
            translateAnimation.duration = 400L
            translateAnimation.interpolator = AccelerateInterpolator()
            mLoginFormView!!.animation = translateAnimation
            mLoginFormView!!.animation = translateAnimation
            val translateAnimation2 = TranslateAnimation(loadingView!!.right.toFloat(), 0f, 0f, 0f)
            translateAnimation2.fillAfter = true
            translateAnimation2.duration = 400L
            translateAnimation2.interpolator = AccelerateInterpolator()
            loadingView!!.animation = translateAnimation2
            loadingView!!.show()
        } else {
            val translateAnimation = TranslateAnimation((-mLoginFormView!!.right).toFloat(), 0f, 0f, 0f)
            translateAnimation.fillAfter = true
            translateAnimation.duration = 400L
            translateAnimation.interpolator = AccelerateInterpolator()
            mLoginFormView!!.animation = translateAnimation
            val translateAnimation2 = TranslateAnimation(0f, loadingView!!.right.toFloat(), 0f, 0f)
            translateAnimation2.fillAfter = true
            translateAnimation2.duration = 400L
            translateAnimation2.interpolator = AccelerateInterpolator()
            loadingView!!.animation = translateAnimation2
            loadingView!!.hide()
        }
    }


//    private fun addEmailsToAutoComplete(emailAddressCollection: List<String>) {
//        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
//        val adapter = ArrayAdapter(this@LoginActivity,
//                android.R.layout.simple_dropdown_item_1line, emailAddressCollection)
//
//        mAccountView!!.setAdapter(adapter)
//    }


    private interface ProfileQuery {
        companion object {
            val PROJECTION = arrayOf(ContactsContract.CommonDataKinds.Email.ADDRESS, ContactsContract.CommonDataKinds.Email.IS_PRIMARY)

            val ADDRESS = 0
            val IS_PRIMARY = 1
        }
    }

//    private fun loginUser(muser: String, passwd: String) {
//        Observable.create(Observable.OnSubscribe<Int> { subscriber ->
//            val user = User()
//            user.passwd = passwd
//            user.stuNo = muser
//            val dbManager = DBManager(applicationContext)
//            val users = dbManager.queryUserList()
//            var isExist = false
//            for (resUser in users) {
//                Log.i(TAG, "user " + resUser.stuNo)
//                if (resUser.stuNo == user.stuNo) {
//                    resUser.passwd = user.passwd
//                    resUser.setIsLogin(true)
//                    dbManager.updateUser(resUser)
//                    isExist = true
//                    break
//                }
//            }
//            if (!isExist) {
//                user.setIsLogin(true)
//                dbManager.insertUser(user)
//            }
//            DefaultConfig.get(applicationContext).stuNo = user.stuNo
//            var loginResponse = 0
//            try {
//                loginResponse = Login.loginUser(user)
//            } catch (e: Exception) {
//                subscriber.onError(e)
//            }
//            subscriber.onNext(loginResponse)
//        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Int> {
//            override fun onCompleted() {
//
//            }
//
//            override fun onError(e: Throwable) {
//                e.printStackTrace()
//                Toast.makeText(applicationContext, "登录失败,请检查网络连接", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNext(loginResponse: Int?) {
//                when (loginResponse) {
//                    1 -> {
//                        editor = pref!!.edit()
//                        if (mRem_passwords!!.isChecked) {
//                            editor!!.putBoolean("remember_password", true)
//                            editor!!.putString("account", muser)
//                            editor!!.putString("password", passwd)
//                        } else {
//                            editor!!.clear()
//                        }
//                        editor!!.apply()
//                        val intent = Intent(this@LoginActivity, ManagerMainActivity::class.java)
//                        startActivity(intent)
//                        finish()
//                        return
//                    }
//                    else -> Log.i(TAG, "未知错误")
//                }
//                Toast.makeText(applicationContext, loginResponse!!, Toast.LENGTH_SHORT).show()
//                mPasswordView!!.error = ResultCode.map[loginResponse]
//                mPasswordView!!.requestFocus()
//                showProgress(false)
//            }
//        })
//    }

    private fun loginDepManager(managerAccount: String, passwd: String) {
        Observable.create(Observable.OnSubscribe<Int> { subscriber ->
            val depManager = DepManager()
            depManager.password= passwd
            depManager.depManagerAccount = managerAccount
            try {
                var loginResponse = DepManagerApi.loginManager(applicationContext,depManager.depManagerAccount,depManager.password);
                if (loginResponse.code==ResultCode.SUCCESS){
                    DepManagerLab.get(applicationContext).depManagerDto=loginResponse.`object`;
                    DefaultConfig.get(applicationContext).depmanagerId= loginResponse.`object`.depManagerId.toInt();
                    println("depId"+loginResponse.`object`.departmentId)
                    if (loginResponse.`object`.departmentId == 0L) {
                        subscriber.onNext(1000)
                        return@OnSubscribe
                    }
                }
                subscriber.onNext(loginResponse.code)
            } catch (e: Exception) {
                subscriber.onError(e)
            }

        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<Int> {
            override fun onCompleted() {

            }

            override fun onError(e: Throwable) {
                e.printStackTrace()
                Toast.makeText(applicationContext, "登录失败,请检查网络连接", Toast.LENGTH_SHORT).show()
                showProgress(false)
            }

            override fun onNext(loginResponse: Int?) {
                when (loginResponse) {
                    1 -> {
//                        editor = pref!!.edit()
//                        if (mRem_passwords!!.isChecked) {
//                            editor!!.putBoolean("remember_password", true)
//                            editor!!.putString("account",managerAccount )
//                            editor!!.putString("password", passwd)
//                        } else {
//                            editor!!.clear()
//                        }
//                        editor!!.apply()
                        val intent = Intent(this@LoginActivity, ManagerMainActivity::class.java)
                        startActivity(intent)
                        finish()
                        return
                    }
                    1000->{
                        val intent = Intent(this@LoginActivity, AddDepartmentActivity::class.java)
                        startActivity(intent)
                        finish()
                        return
                    }
                    else -> Log.i(TAG, ResultCode.map[loginResponse])
                }
                Toast.makeText(applicationContext, ResultCode.map[loginResponse], Toast.LENGTH_SHORT).show()
                mPasswordView!!.error = ResultCode.map[loginResponse]
                mPasswordView!!.requestFocus()
                showProgress(false)
            }
        })
    }


}
