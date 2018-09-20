package com.boyu.emove.goods.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boyu.emove.R
import com.boyu.emove.extension.inflate
import com.boyu.emove.goods.entity.AllGood
import com.boyu.emove.goods.entity.CartGood
import com.boyu.emove.goods.entity.SecondCategory
import kotlinx.android.synthetic.main.item_cell_category.view.*
import kotlinx.android.synthetic.main.item_cell_good.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/19.
 */
class GoodsAdapter
@Inject constructor() : RecyclerView.Adapter<GoodsAdapter.ViewHolder>() {

    internal var plusClickListener: (goodId: Int) -> Unit = {_ -> }
    internal var minusClickListener: (goodId: Int) -> Unit = {_ -> }

    internal var data: Pair<List<SecondCategory>, List<AllGood>> by Delegates.observable(Pair(ArrayList(), ArrayList())) {
        _, _, _ ->

        for(second_category in data.first) {
            items.add(Item(0, second_category.category_name, ItemType.SUBCATEGORY, 0))
            for(good in data.second) {
                if(good.parent_category_id == second_category.category_id){
                    items.add(Item(good.goods_id, good.goods_name, ItemType.GOOD, getGoodsNumber(good.goods_id)))
                }
            }
        }
        notifyDataSetChanged()
    }

    internal var cart: List<CartGood> = ArrayList()

    private var items = arrayListOf<Item>()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsAdapter.ViewHolder {
        if (viewType == ItemType.SUBCATEGORY.ordinal){
            return ViewHolder(parent.inflate(R.layout.item_cell_category), viewType)
        }
        return ViewHolder(parent.inflate(R.layout.item_cell_good), viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].categoryType.ordinal
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: GoodsAdapter.ViewHolder, position: Int) {
        if(items[position].categoryType == ItemType.SUBCATEGORY){
            holder.tvText?.text = items[position].categoryName
        }else {
            holder.tvText?.text = items[position].categoryName
            holder.tvNumber?.text = items[position].number.toString()
            if(items[position].number == 0){
                holder.btnMinus?.visibility = View.INVISIBLE
                holder.tvNumber?.visibility = View.INVISIBLE
            }else {
                holder.btnMinus?.visibility = View.VISIBLE
                holder.tvNumber?.visibility = View.VISIBLE
            }

            holder.btnPlus?.setOnClickListener  {
                plusClickListener(items[position].goodId)
            }

            holder.btnMinus?.setOnClickListener  {
                minusClickListener(items[position].goodId)
            }
        }
    }

    private fun getGoodsNumber(goodId: Int): Int{
        for(item in cart) {
            if (item.goods_id == goodId) {
                return item.goods_num
            }
        }
        return 0
    }

    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var tvText: TextView? = null
        var btnPlus: Button? = null
        var btnMinus: Button? = null
        var tvNumber: TextView? = null

        init {
            if (viewType == ItemType.SUBCATEGORY.ordinal){
                tvText = itemView.tv_category_text
            }else {
                btnPlus = itemView.btn_add
                btnMinus = itemView.btn_minus
                tvText = itemView.tv_goods_text
                tvNumber = itemView.tv_good_number
            }
        }
    }

    data class Item( var goodId: Int, var categoryName: String, var categoryType: ItemType, var number: Int)

    enum class ItemType {
        SUBCATEGORY, GOOD
    }
}