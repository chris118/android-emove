package com.boyu.emove.Login.ui

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.boyu.emove.Login.viewmodel.LoginViewModel
import com.boyu.emove.base.BaseActivity
import com.boyu.emove.main.ui.MainActivity
import com.boyu.emove.R
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class LoginActivity : BaseActivity() {
    private val TAG = LoginActivity::class.java.simpleName
    private var counter = 5

    var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        appComponent.inject(this)

        if(viewModel != null ){
            Log.d(TAG, "hello")
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
        }

        btn_login.setOnClickListener {
            startActivity<MainActivity>()
        }
    }
}
