package com.boyu.emove.vehicle.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.OptionsPickerView
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.extension.toast
import com.boyu.emove.goods.ui.adapter.CategoryAdapter
import com.boyu.emove.vehicle.ui.adapter.VehicleAdapter
import com.boyu.emove.vehicle.viewmodel.VehicleViewModel
import kotlinx.android.synthetic.main.fragment_goods.*
import kotlinx.android.synthetic.main.fragment_info_ex.*
import kotlinx.android.synthetic.main.fragment_vehicle.*
import kotlinx.android.synthetic.main.item_cell_vehicle.*
import javax.inject.Inject


class VehicleFragment : BaseNaviFragment() {
    private val TAG = VehicleFragment::class.java.simpleName
    private var viewModel: VehicleViewModel? = null
    private val orderOptions = arrayOf("订单最多", "评分最高", "离我最近")
    private val orderValues = arrayOf("order","evaluate","none")
    private var selectedOrderValue = "order"

    private var orderPicker: OptionsPickerView<String>? = null


    @Inject
    lateinit var vehicleAdapter: VehicleAdapter

    override fun onNext() {
        viewModel?.updateVehicle(vehicleAdapter.selectedFleet)
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_vehicleFragment_to_orderFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.getVehicleResponse.observe(this@VehicleFragment, Observer {
                if (it.code == 0){
                    Log.d(TAG, "success getVehicleResponse")
                    vehicleAdapter.selectedFleet = it.result.selected_fleet_id
                    vehicleAdapter.data = it.result.usable_fleet
                }else {
                    it.msg.toast(activity!!)
                }
            })

            this.updateVehicleResponse.observe(this@VehicleFragment, Observer {
                if (it.code == 0){
                    goNext()
                }else {
                    it.msg.toast(activity!!)
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_vehicle, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeView()
        loadData()
    }

    private fun initializeView() {
        vehicleRecycleView.layoutManager = LinearLayoutManager(activity!!)
        vehicleRecycleView.adapter = vehicleAdapter
        vehicleRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))


        tv_vehicle_order.setOnClickListener {
            orderPicker?.show()
        }

        orderPicker = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    selectedOrderValue = orderValues[option1]
                    tv_vehicle_order.text = orderOptions[option1] + " 三"
                    loadData()
                }).build() as OptionsPickerView<String>
        orderPicker?.setPicker(orderOptions.toMutableList())
    }

    private fun loadData() {
        viewModel?.getVehicle(selectedOrderValue)
    }
}
