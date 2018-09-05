package com.boyu.emove.base

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.boyu.emove.AndroidApplication
import com.boyu.emove.di.ApplicationComponent

/**
 * Created by chrisw on 2018/8/23.
 */
open class BaseActivity: AppCompatActivity() {
    val appComponent : ApplicationComponent by lazy{
        (this.application as AndroidApplication).appComponent
    }

    inline fun<reified T: Activity> startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}