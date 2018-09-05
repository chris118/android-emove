package com.boyu.emove.di

import com.boyu.emove.AndroidApplication
import com.boyu.emove.Login.ui.LoginActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by chrisw on 2018/8/31.
 */

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: AndroidApplication)
    fun inject(movieDetailsFragment: LoginActivity)

}