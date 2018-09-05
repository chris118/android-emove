package com.boyu.emove.base

import androidx.fragment.app.Fragment
import com.boyu.emove.AndroidApplication
import com.boyu.emove.di.ApplicationComponent

/**
 * Created by chrisw on 2018/9/4.
 */
abstract class BaseFragment: Fragment() {
    val appComponent : ApplicationComponent by lazy{
        (activity?.application as AndroidApplication).appComponent
    }

    abstract fun getTargetLayoutId(): Int
}