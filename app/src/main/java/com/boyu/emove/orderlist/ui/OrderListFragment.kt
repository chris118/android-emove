package com.boyu.emove.orderlist.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.OptionsPickerView
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseFragment
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.orderlist.ui.adapter.OrderListAdapter
import com.boyu.emove.orderlist.viewmodel.OrderListViewModel
import kotlinx.android.synthetic.main.fragment_order_list.*
import kotlinx.android.synthetic.main.fragment_vehicle.*
import javax.inject.Inject

class OrderListFragment : BaseFragment() {

    private val TAG = OrderListFragment::class.java.simpleName
    private var viewModel: OrderListViewModel? = null

    @Inject
    lateinit var orderListAdapter: OrderListAdapter

    private val orderOptions = arrayOf("全部订单", "未完成订单", "完成订单")
    private val orderValues = arrayOf("none","wait","finish")
    private var selectedOrderValue = "none"
    private var orderPicker: OptionsPickerView<String>? = null

    override fun getTargetLayoutId(): Int {
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.getOrderListResponse.observe(this@OrderListFragment, Observer {
                if (it.code == 0){
                    orderListAdapter.data = it.result
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_order_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeView()
        loadData()
    }

    private fun initializeView() {
        orderlistRecycleView.layoutManager = LinearLayoutManager(activity!!)
        orderlistRecycleView.adapter = orderListAdapter
        orderlistRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))

        tv_order_list.setOnClickListener {
            orderPicker?.show()
        }

        orderPicker = OptionsPickerView.Builder(activity,
                OptionsPickerView.OnOptionsSelectListener { option1: Int, option2: Int, option3: Int, v: View? ->
                    selectedOrderValue = orderValues[option1]
                    tv_order_list.text = orderOptions[option1] + " 三"
                    loadData()
                }).build() as OptionsPickerView<String>
        orderPicker?.setPicker(orderOptions.toMutableList())
    }

    private fun loadData() {
        viewModel?.getOrderList(1, selectedOrderValue)
    }
}
