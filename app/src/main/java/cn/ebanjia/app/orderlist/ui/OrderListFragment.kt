package cn.ebanjia.app.orderlist.ui

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
import cn.ebanjia.app.R
import cn.ebanjia.app.base.ui.BaseFragment
import cn.ebanjia.app.base.ui.BaseNaviFragment
import cn.ebanjia.app.base.ui.DividerItemDecoration
import cn.ebanjia.app.extension.createViewModel
import cn.ebanjia.app.extension.toast
import cn.ebanjia.app.main.ui.MainActivity
import cn.ebanjia.app.orderlist.ui.adapter.OrderListAdapter
import cn.ebanjia.app.orderlist.viewmodel.OrderListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_order_list.*
import org.jetbrains.anko.bundleOf
import javax.inject.Inject
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import android.widget.Toast
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.R.attr.description
import cn.sharesdk.onekeyshare.OnekeyShare
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.WXAPIFactory


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

        initializeView()
    }

    override fun onResume() {
        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        activity!!.bnv_bottom_navigation.visibility = View.VISIBLE

        super.onResume()
        loadData()
    }

    private fun initializeView() {
        orderlistRecycleView.layoutManager = LinearLayoutManager(activity!!)
        orderlistRecycleView.adapter = orderListAdapter
        orderlistRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))

        orderListAdapter.orderClickListener = {
            activity!!.bnv_bottom_navigation.visibility = View.GONE

            val action = OrderListFragmentDirections.ActionOrderListFragment2ToOrderFragment2()
            action.setOrderId(it)
            val navigation =  Navigation.findNavController(activity!!, R.id.nav_host_fragment)
            navigation.navigate( action)

        }

        orderListAdapter.itemClickListener = {
            activity!!.bnv_bottom_navigation.visibility = View.GONE

            val action = OrderListFragmentDirections.ActionOrderListFragment2ToOrderFragment2()
            action.setOrderId(it)
            val navigation =  Navigation.findNavController(activity!!, R.id.nav_host_fragment)
            navigation.navigate( action)
        }

        orderListAdapter.kanjiaClickListener = {
            activity!!.bnv_bottom_navigation.visibility = View.GONE

//            val action = OrderListFragmentDirections.ActionOrderListFragmentToKanjiaFragment()
//            action.setOrderId(it)
//
//            val navigation =  Navigation.findNavController(activity!!, R.id.nav_host_fragment)
//            navigation.navigate(action)


//            val mWXApi = WXAPIFactory.createWXAPI(context, "wx57d8abaf6729936a")
//            mWXApi.registerApp("wx57d8abaf6729936a")
//
//            val webpage = WXWebpageObject()
//            webpage.webpageUrl = "https://www.ebanjia.cn/cut/" + id.toString()
//            val msg = WXMediaMessage(webpage)
//            msg.title = "kanjia"
//            msg.description = "kanjia"
//
////            val thumb = BitmapFactory.decodeResource(mContext.getResources(), shareContent.getPictureResource())
////            if (thumb == null) {
////                Toast.makeText(mContext, "图片不能为空", Toast.LENGTH_SHORT).show()
////            } else {
////                msg.thumbData = Util.bmpToByteArray(thumb, true)
////            }
//
//            val req = SendMessageToWX.Req()
//            req.transaction = "webpage" + System.currentTimeMillis()
//            req.message = msg
//            req.scene = SendMessageToWX.Req.WXSceneSession
//            mWXApi.sendReq(req)


            val oks = OnekeyShare()
            //关闭sso授权
            oks.disableSSOWhenAuthorize()

            // title标题，微信、QQ和QQ空间等平台使用
            oks.setTitle("砍价分享")
            // text是分享文本，所有平台都需要这个字段
            oks.setText("砍价分享砍价分享砍价分享砍价分享")

            //网络图片的url：所有平台
            oks.setImageUrl("http://www.ebanjia.cn/statics/front/images/article-thumbnail.png")

            // url在微信、微博，Facebook等平台中使用
            oks.setUrl("https://www.ebanjia.cn/cut/$it")

            // 启动分享GUI
            oks.show(activity!!)
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
