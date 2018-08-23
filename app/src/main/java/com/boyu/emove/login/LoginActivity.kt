package com.boyu.emove.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.boyu.emove.Base.BaseActivity
import com.boyu.emove.Main.MainActivity
import com.boyu.emove.R
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity() {
    private val TAG = LoginActivity::class.java.simpleName
    private var counter = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
