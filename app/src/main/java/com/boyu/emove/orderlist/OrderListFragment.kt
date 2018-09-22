package com.boyu.emove.orderlist

import android.os.Bundle

import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.orderlist.viewmodel.OrderListViewModel

class OrderListFragment : BaseNaviFragment() {

    private val TAG = OrderListFragment::class.java.simpleName
    private var viewModel: OrderListViewModel? = null

    override fun onNext() {
    }

    override fun getTargetLayoutId(): Int {
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {

        }
    }
}
