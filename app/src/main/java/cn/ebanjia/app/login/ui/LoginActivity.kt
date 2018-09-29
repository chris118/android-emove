package cn.ebanjia.app.login.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import cn.ebanjia.app.login.interactor.LoginInteractor
import cn.ebanjia.app.login.viewmodel.LoginViewModel
import cn.ebanjia.app.R
import cn.ebanjia.app.base.ui.BaseActivity
import cn.ebanjia.app.extension.createViewModel
import cn.ebanjia.app.extension.toast
import cn.ebanjia.app.main.ui.MainActivity
import cn.ebanjia.app.utils.SharedPreferencesUtil
import kotlinx.android.synthetic.main.activity_login.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {
    private val TAG = LoginActivity::class.java.simpleName
    private var counter = 60
    private var token by SharedPreferencesUtil(this@LoginActivity,"token","")
    private var uid by SharedPreferencesUtil(this@LoginActivity,"uid","")

    private var viewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.sendVerifyCodeResponse.observe(this@LoginActivity, Observer {
                if (it.code != 0) {
                    it.msg.toast(this@LoginActivity)
                }else {
                    it.msg.toast(this@LoginActivity)
                }
            })

            this.loginResponse.observe(this@LoginActivity, Observer {
                if (it.code == 0) {
                    Log.d(TAG, "token = ${it.result.token}")
                    token = it.result.token
                    uid = it.result.uid.toString()
                    startActivity<MainActivity>()
                }else {
                    it.msg.toast(this@LoginActivity)
                }
            })
        }

        initializeView()
    }

    private fun initializeView() {
        tv_code.setOnClickListener {
            tv_code.isEnabled = false
            val executor = Executors.newScheduledThreadPool(1)
            executor.scheduleAtFixedRate({
                counter--
                runOnUiThread {
                    tv_code.text = counter.toString()
                    if(counter == 0){
                        tv_code.isEnabled = true
                        executor.shutdown()
                        counter = 60
                        tv_code.text = "获取验证码"
                    }
                }

            }, 0, 1, TimeUnit.SECONDS)

            viewModel?.sendVerifyCode(mobile.text.toString())
        }

        btn_login.setOnClickListener {
            val params = LoginInteractor.Params(mobile.text.toString(), code.text.toString())
            viewModel?.login(params)
        }
    }
}
