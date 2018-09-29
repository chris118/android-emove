package cn.ebanjia.app

import android.app.Application
import com.baidu.mapapi.SDKInitializer
import cn.ebanjia.app.di.ApplicationComponent
import cn.ebanjia.app.di.ApplicationModule
import cn.ebanjia.app.di.DaggerApplicationComponent
import com.mob.MobSDK
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

        MobSDK.init(this)

        //初始化crash统计
        CrashReport.initCrashReport(this, "9e3ed21a73", true)
        SDKInitializer.initialize(this)
    }
}