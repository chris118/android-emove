package com.boyu.emove.Base

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by chrisw on 2018/8/23.
 */
open class BaseActivity: AppCompatActivity() {
    inline fun<reified T: Activity> startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}