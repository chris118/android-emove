package com.boyu.emove.Login.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.boyu.emove.Login.viewmodel.LoginViewModel
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseActivity
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.main.ui.MainActivity
import com.boyu.emove.utils.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {
    private val TAG = LoginActivity::class.java.simpleName
    private var counter = 5
    private var token by SharedPreferencesUtil(this@LoginActivity,"token","")
    private var uid by SharedPreferencesUtil(this@LoginActivity,"uid","")

    var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.sendVerifyCodeResponse.observe(this@LoginActivity, Observer {
                Log.d(TAG, "$(it.)")

            })
        }

        initUI()
    }

    private fun initUI() {
        tv_code.setOnClickListener {
            tv_code.isEnabled = false
            val executor = Executors.newScheduledThreadPool(1)
            executor.scheduleAtFixedRate({
                counter--
                tv_code.text = counter.toString()
                if(counter == 0){
                    tv_code.isEnabled = true
                    executor.shutdown()
                    counter = 5
                    tv_code.text = "获取验证码"
                }
            }, 0, 1, TimeUnit.SECONDS)

            viewModel?.sendVerifyCode("15618516930")
        }

        btn_login.setOnClickListener {
            startActivity<MainActivity>()
        }
    }
}
