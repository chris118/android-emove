package com.boyu.emove.di

import com.boyu.emove.AndroidApplication
import com.boyu.emove.Login.ui.LoginActivity
import com.boyu.emove.goods.ui.GoodsFragment
import com.boyu.emove.info.ui.AddressActivity
import com.boyu.emove.info.ui.InfoFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by chrisw on 2018/8/31.
 */

@Singleton
@Component(modules = [ApplicationModule::class, ViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(loginActivity: LoginActivity)
    fun inject(infoFragment: InfoFragment)
    fun inject(infoFragment: AddressActivity)
    fun inject(goodsFragment: GoodsFragment)
}