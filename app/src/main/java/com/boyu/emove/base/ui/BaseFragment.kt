package com.boyu.emove.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.boyu.emove.AndroidApplication
import com.boyu.emove.R
import com.boyu.emove.di.ApplicationComponent
import kotlinx.android.synthetic.main.activity_main.*
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val navController =  Navigation.findNavController(activity!!, R.id.fl_container)
        NavigationUI.setupActionBarWithNavController((activity as? AppCompatActivity)!!, navController)
    }
}