package com.boyu.emove.order


import android.os.Bundle
import androidx.fragment.app.Fragment
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.order.viewmodel.OrderViewModel
import com.boyu.emove.vehicle.ui.VehicleFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class OrderFragment : BaseNaviFragment() {
    private val TAG = VehicleFragment::class.java.simpleName
    private var viewModel: OrderViewModel? = null


    override fun onNext() {
        goNext()
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
