package com.boyu.emove.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.boyu.emove.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by chrisw on 2018/8/28.
 */
abstract class BaseNaviFragment(): Fragment() {
    abstract fun getTargetLayoutId(): Int

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_item_next, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.goodsFragment -> {
                val navigation =  Navigation.findNavController(activity!!, R.id.nav_host_fragment)
                navigation.navigate(getTargetLayoutId())

//                NavigationUI.onNavDestinationSelected(item,
//                        Navigation.findNavController(activity!!, R.id.nav_host_fragment))
//                        || super.onOptionsItemSelected(item)

                activity?.bnv_bottom_navigation?.visibility = View.GONE
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
