package com.boyu.emove

import android.app.Application
import com.baidu.mapapi.SDKInitializer
import com.boyu.emove.di.ApplicationComponent
import com.boyu.emove.di.ApplicationModule
import com.boyu.emove.di.DaggerApplicationComponent
import com.tencent.bugly.crashreport.CrashReport

/**
 * Created by chrisw on 2018/8/31.
 */
class AndroidApplication : Application() {
    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
    override fun onCreate() {
        super.onCreate()

        //初始化crash统计
        CrashReport.initCrashReport(this, "9e3ed21a73", true)
        SDKInitializer.initialize(this)
    }
}