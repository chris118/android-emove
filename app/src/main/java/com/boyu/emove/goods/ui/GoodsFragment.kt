package com.boyu.emove.goods.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.boyu.emove.R
import com.boyu.emove.base.ui.BaseNaviFragment
import com.boyu.emove.base.ui.DividerItemDecoration
import com.boyu.emove.extension.createViewModel
import com.boyu.emove.goods.entity.AllGood
import com.boyu.emove.goods.entity.CartGood
import com.boyu.emove.goods.entity.FirstCategory
import com.boyu.emove.goods.entity.SecondCategory
import com.boyu.emove.goods.ui.adapter.CategoryAdapter
import com.boyu.emove.goods.ui.adapter.GoodsAdapter
import com.boyu.emove.goods.viewmodel.GoodsViewModel
import kotlinx.android.synthetic.main.fragment_goods.*
import javax.inject.Inject

class GoodsFragment : BaseNaviFragment() {
    private var viewModel: GoodsViewModel? = null

    @Inject
    lateinit var categoryAdapter: CategoryAdapter
    @Inject
    lateinit var goodsAdapter: GoodsAdapter

    private  var  first_category: List<FirstCategory> = ArrayList()
    private  var  second_category: List<SecondCategory> = ArrayList()
    private  var  all_goods: List<AllGood> = ArrayList()
    private  var  cart_goods: List<CartGood> = ArrayList()

    override fun onNext() {
    }

    override fun getTargetLayoutId(): Int {
        return 0
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
                    }
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

        }

        goodsAdapter.plusClickListener = {

        }
    }
}
