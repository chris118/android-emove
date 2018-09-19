package com.boyu.emove.base.ui

import android.view.*
import androidx.navigation.Navigation
import com.boyu.emove.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by chrisw on 2018/8/28.
 */
abstract class BaseNaviFragment : BaseFragment() {

    init {
        setHasOptionsMenu(true)
    }

    abstract fun onNext()

    internal fun goNext() {
        val navigation =  Navigation.findNavController(activity!!, R.id.nav_host_fragment)
        navigation.navigate(getTargetLayoutId())

//                NavigationUI.onNavDestinationSelected(item,
//                        Navigation.findNavController(activity!!, R.id.nav_host_fragment))
//                        || super.onOptionsItemSelected(item)

        activity?.bnv_bottom_navigation?.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_item_next, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.targetFragment -> {
                onNext()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
