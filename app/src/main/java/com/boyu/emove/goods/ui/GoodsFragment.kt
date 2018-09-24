package com.boyu.emove.goods.ui

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.extension.*
import com.boyu.emove.goods.entity.*
import com.boyu.emove.goods.ui.adapter.CartAdapter
import com.boyu.emove.goods.ui.adapter.CategoryAdapter
import com.boyu.emove.goods.ui.adapter.GoodsAdapter
import com.boyu.emove.goods.viewmodel.GoodsViewModel
import kotlinx.android.synthetic.main.fragment_goods.*
import javax.inject.Inject
import com.google.gson.Gson



class GoodsFragment : BaseNaviFragment() {
    private val TAG = GoodsFragment::class.java.simpleName

    private var viewModel: GoodsViewModel? = null

    @Inject
    lateinit var categoryAdapter: CategoryAdapter
    @Inject
    lateinit var goodsAdapter: GoodsAdapter
    @Inject
    lateinit var cartAdapter: CartAdapter

    private  var  first_category: List<FirstCategory> = ArrayList()
    private  var  second_category: List<SecondCategory> = ArrayList()
    private  var  all_goods: List<AllGood> = ArrayList()
    private  var  cart_goods: List<CartGood> = ArrayList()

    //scroll
    private var goodsRecycleView_contentOffset = 0
    private var subCategoryHeightList: MutableList<Int> = ArrayList()

    override fun onNext() {
        goNext()
    }

    override fun getTargetLayoutId(): Int {
        return R.id.action_goodsFragment_to_infoExFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        viewModel = createViewModel(viewModelFactory) {
            this.getGoodsResponse.observe(this@GoodsFragment, Observer {
                if(it.code == 0){
                    with(it.result){
                        this@GoodsFragment.first_category = first_category
                        this@GoodsFragment.second_category = second_category
                        this@GoodsFragment.all_goods = all_goods
                        this@GoodsFragment.cart_goods = cart_goods

                        categoryAdapter.data = Pair(first_category, second_category)
                        goodsAdapter.cart = cart_goods
                        goodsAdapter.data = Pair(second_category, all_goods)
                        cartAdapter.data = cart_goods

                        updateNumbers()
                        calculateHeightOfSubCategory()
                    }
                }else {
                    it.msg.toast(activity!!)
                }
            })

            this.updateGoodsResponse.observe(this@GoodsFragment, Observer {
                if(it.code == 0) {
                    loadData()
                }else {
                    it.msg.toast(activity!!)
                }
            })
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeView()
        loadData()
    }

    private fun loadData() {
        viewModel?.getGoods()
    }

    private fun initializeView() {
        categoryRecycleView.layoutManager = LinearLayoutManager(activity!!)
        categoryRecycleView.adapter = categoryAdapter
        categoryRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))

        goodRecycleView.layoutManager = LinearLayoutManager(activity!!)
        goodRecycleView.adapter = goodsAdapter
        goodRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))

        goodsAdapter.minusClickListener = {
            this@GoodsFragment.updateCart(it, false)
        }

        goodsAdapter.plusClickListener = {
            this@GoodsFragment.updateCart(it, true)
        }

        goodRecycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                goodsRecycleView_contentOffset += dy
                //滚动到的类别index
                var index = 0
                var total_height = 0
                for ((i, item) in subCategoryHeightList.withIndex() ){
                    total_height += item

                    val offsetDp = goodsRecycleView_contentOffset.toDp
                    Log.d(TAG, offsetDp.toString())

                    if(offsetDp >= total_height){
                        index = i + 1
                    }
                }
                categoryAdapter.selectGoodId = second_category[index].category_id
            }
        })


        cartRecycleView.layoutManager = LinearLayoutManager(activity!!)
        cartRecycleView.adapter = cartAdapter
        cartRecycleView.addItemDecoration(DividerItemDecoration(activity!!, DividerItemDecoration.VERTICAL))
        cartAdapter.minusClickListener = {
            this@GoodsFragment.updateCart(it, false)
        }

        cartAdapter.plusClickListener = {
            this@GoodsFragment.updateCart(it, true)
        }

        rl_cart_button.setOnClickListener  {
            rl_cart_selected.visibility = if(rl_cart_selected.visibility == View.GONE) View.VISIBLE else View.GONE

        }
        ll_mask.setOnClickListener {
            rl_cart_selected.visibility = View.GONE
        }
    }

    private fun updateCart(good_id: Int, add: Boolean){

        var step = if(add) 1 else -1
        var fFoundItem = false
        //更新购物车 (购物车中有item，更新数量)
        var cart_goods_temp = ArrayList<CartGood>()
        for(item in cart_goods) {
            if (item.goods_id == good_id) {
                item.goods_num = item.goods_num + step
                fFoundItem = true
            }
            if (item.goods_num >= 0 ) {
                cart_goods_temp.add(item)
            }
        }
        //增加时 购物车中没有item，则新插入一条
        if (add){
            if (fFoundItem == false) {
                var cartGood = CartGood(good_id, 1, "", 0.0, 0)
                cart_goods_temp.add(cartGood)
            }
        }

        val gson = Gson()
        val jsonString = gson.toJson(cart_goods_temp)
        val cart = CartGoodBody(jsonString)

        viewModel?.updateGoods(cart)
    }

    private fun updateNumbers() {
        //更新红点数量 更新体积
        var badge_count = 0
        var bulk_count = 0.0
        for (item in cart_goods) {
            badge_count += item.goods_num
            bulk_count += item.goods_cubage
        }
        tv_badge.text = badge_count.toString()
        if(badge_count == 0) {
            tv_badge.visibility = View.INVISIBLE
        } else {
            tv_badge.visibility = View.VISIBLE
        }
        tv_bulk.text = String.format("%.2f",bulk_count)
    }

    private fun calculateHeightOfSubCategory() {
        subCategoryHeightList.clear()
        for (_secondCategory in second_category) {
            var item_count = 0
            for( item in all_goods) {
                if(item.parent_category_id == _secondCategory.category_id){
                    item_count += 1
                }
            }

            subCategoryHeightList.add(44 * (item_count + 1))
        }
    }
}
