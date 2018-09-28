package com.boyu.emove.base.ui

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
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
        val navigation =  Navigation.findNavController(activity!!, R.id.fl_container)
        navigation.navigate(getTargetLayoutId())

        activity?.bnv_bottom_navigation?.visibility = View.GONE
    }

    open fun menuItem(): Int {
        return R.menu.menu_item_next
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(menuItem(), menu)
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
