package com.boyu.emove

import android.app.Application
import com.baidu.mapapi.SDKInitializer
import com.boyu.emove.di.ApplicationComponent
import com.boyu.emove.di.ApplicationModule
import com.boyu.emove.di.DaggerApplicationComponent

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

        SDKInitializer.initialize(this)
    }
}