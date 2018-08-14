package com.boyu.emove.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.boyu.emove.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        tv_code.setOnClickListener {
            Log.d(TAG, "code")
        }
    }
}
