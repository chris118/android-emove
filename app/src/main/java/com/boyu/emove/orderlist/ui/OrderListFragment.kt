package com.boyu.emove.orderlist.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigkoo.pickerview.OptionsPickerView
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseFragment
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.extension.toast
import com.boyu.emove.main.ui.MainActivity
import com.boyu.emove.orderlist.ui.adapter.OrderListAdapter
import com.boyu.emove.orderlist.viewmodel.OrderListViewModel
import kotlinx.android.synthetic.main.fragment_order_list.*
import org.jetbrains.anko.bundleOf
import javax.inject.Inject

class OrderListFragment : BaseNaviFragment() {
    override fun onNext() {
    }

    override fun menuItem(): Int {
        return R.menu.menu_item_null
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_orderListFragment2_to_orderFragment2
    }

    private val TAG = OrderListFragment::class.java.simpleName
    private var viewModel: OrderListViewModel? = null

    @Inject
    lateinit var orderListAdapter: OrderListAdapter

    private val orderOptions = arrayOf("全部订单", "未完成订单", "完成订单")
    private val orderValues = arrayOf("none","wait","finish")
    private var selectedOrderValue = "none"
    private var orderPicker: OptionsPickerView<String>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.getOrderListResponse.observe(this@OrderListFragment, Observer {
                if (it.code == 0){
                    orderListAdapter.data = it.result
                }else {
                    it.msg.toast(activity!!)
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

        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        initializeView()
        loadData()
    }

    private fun initializeView() {
        orderlistRecycleView.layoutManager = LinearLayoutManager(activity!!)
        orderlistRecycleView.adapter = orderListAdapter
        orderlistRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))

        orderListAdapter.orderClickListener = {

            val action = OrderListFragmentDirections.ActionOrderListFragment2ToOrderFragment2()
            action.setOrderId(it)
            val navigation =  Navigation.findNavController(activity!!, R.id.nav_host_fragment)
            navigation.navigate( action)
        }

        orderListAdapter.kanjiaClickListener = {

        }

        tv_order_list.setOnClickListener {
            orderPicker?.show()
        }

        btn_hotline.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL)
            val data = Uri.parse("tel:" + "400-000-6668")
            intent.data = data;
            startActivity(intent)
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
