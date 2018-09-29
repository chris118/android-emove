package cn.ebanjia.app.base.ui

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cn.ebanjia.app.AndroidApplication
import cn.ebanjia.app.di.ApplicationComponent
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/4.
 */
abstract class BaseFragment: Fragment() {
    val appComponent : ApplicationComponent by lazy{
        (activity?.application as AndroidApplication).appComponent
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    abstract fun getTargetLayoutId(): Int
}