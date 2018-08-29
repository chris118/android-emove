package com.boyu.emove.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.boyu.emove.R
import com.boyu.emove.base.BaseNaviFragment

class InfoFragment : BaseNaviFragment() {
    companion object {
        fun newInstance() = InfoFragment()
    }

    private lateinit var viewModel: InfoViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
//        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(InfoViewModel::class.java)
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_infoFragment_to_goodsFragment
    }


//    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
//        inflater?.inflate(R.menu.menu_item_next, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        return when (item?.itemId) {
//            R.id.goodsFragment -> {
//                val navigation =  Navigation.findNavController(activity!!, R.id.nav_host_fragment)
//                navigation.navigate(R.id.action_infoFragment_to_goodsFragment)
//
////                NavigationUI.onNavDestinationSelected(item,
////                        Navigation.findNavController(activity!!, R.id.nav_host_fragment))
////                        || super.onOptionsItemSelected(item)
//
//                activity?.bnv_bottom_navigation?.visibility = View.GONE
//                return true
//            }
//            else -> super.onOptionsItemSelected(item)
//        }
//    }
}
