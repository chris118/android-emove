package com.boyu.emove.order.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.extension.toast
import com.boyu.emove.main.ui.MainActivity
import com.boyu.emove.order.entity.Order
import com.boyu.emove.order.ui.adapter.BaseInfoAdapter
import com.boyu.emove.order.ui.adapter.GoodsInfoAdapter
import com.boyu.emove.order.ui.adapter.TotalInfoAdapter
import com.boyu.emove.order.viewmodel.OrderViewModel
import com.boyu.emove.vehicle.ui.VehicleFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_order.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class OrderFragment : BaseNaviFragment() {
    private val TAG = VehicleFragment::class.java.simpleName
    private var viewModel: OrderViewModel? = null
    private var order: Order? = null
    private var order_id = 0

    @Inject
    lateinit var baseInfoAdapter: BaseInfoAdapter

    @Inject
    lateinit var goodsInfoAdapter: GoodsInfoAdapter

    @Inject
    lateinit var totalInfoAdapter: TotalInfoAdapter


    override fun onNext() {
        viewModel?.saveOrder()
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_orderListFragment2_to_orderFragment2
    }

    override fun menuItem(): Int {
        return R.menu.menu_item_submit
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        super.onPrepareOptionsMenu(menu)
        if(order_id > 0){
            menu?.getItem(0)?.isVisible = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
        viewModel = createViewModel(viewModelFactory) {
            this.getOrderResponse.observe(this@OrderFragment, Observer {
                if(it.code == 0){
                    order = it.result
                    updateUI()
                }else {
                    it.msg.toast(activity!!)
                }
            })

            this.saveOrderResponse.observe(this@OrderFragment, Observer {
                if(it.code == 0){
                    order_id = it.result.order_id

                    //隐藏提交
                    activity?.invalidateOptionsMenu()

                    //隐藏返回
                    (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

                    //显示底部导航2个按钮
                    ll_order_bottom.visibility = View.VISIBLE
                }else {
                    it.msg.toast(activity!!)
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            val safeArgs = OrderFragmentArgs.fromBundle(it)
            order_id = safeArgs.orderId
        }

        initializeView()
        loadData()
    }

    private fun initializeView() {
        baseInfoRecycleView.layoutManager = LinearLayoutManager(activity!!)
        baseInfoRecycleView.adapter = baseInfoAdapter
        baseInfoRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))

        goodsInfoRecycleView.layoutManager = LinearLayoutManager(activity!!)
        goodsInfoRecycleView.adapter = goodsInfoAdapter
        goodsInfoRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))

        totalInfoRecycleView.layoutManager = LinearLayoutManager(activity!!)
        totalInfoRecycleView.adapter = totalInfoAdapter
        totalInfoRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))

        btn_order_go_list.setOnClickListener {
//            (activity as? MainActivity)?.showActionBar()
            activity?.let {
                it.bnv_bottom_navigation.selectedItemId = R.id.orderListFragment

                //显示底部导航
                it.bnv_bottom_navigation?.visibility = View.VISIBLE
            }
        }

        btn_order_kanjia.setOnClickListener{

        }
    }

    private fun loadData() {
        if(order_id > 0 ){
            viewModel?.getOrderWithId(order_id)
        }else {
            viewModel?.getOrder()
        }
    }

    private fun updateUI() {
        tv_order_contact_value.text = order?.user_name
        tv_order_mobile_value.text = order?.user_telephone
        tv_order_time_value.text = order?.moving_time
        tv_order_out_value.text = order?.moveout_address +  " (" + if(order?.moveout_is_elevator == 0) "无" else "有" + "电梯" + order?.moveout_distance_meter + "米)"
        tv_order_in_value.text = order?.movein_address +  " (" + if(order?.movein_is_elevator == 0) "无" else "有" + "电梯" + order?.moveout_distance_meter + "米)"
        tv_order_invoice_value.text = "已选择" + if(order?.is_invoice == 0) "不需要" else "需要" + "发票"
        tv_order_note_value.text = order?.user_note
        tv_order_fleet_value.text = order?.fleet_name
        tv_order_fleet_mobile_value.text = order?.fleet_telephone
        tv_order_fleet_address_value.text = order?.fleet_address

        baseInfoAdapter.data = order?.base_info ?: ArrayList()
        goodsInfoAdapter.data = order?.goods_info ?: ArrayList()
        totalInfoAdapter.data = order?.total_info ?: ArrayList()
    }
}
