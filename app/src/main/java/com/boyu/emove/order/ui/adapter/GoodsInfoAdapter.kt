package com.boyu.emove.order.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boyu.emove.R
import com.boyu.emove.extension.inflate
import com.boyu.emove.order.entity.BaseInfo
import com.boyu.emove.order.entity.GoodsInfo
import kotlinx.android.synthetic.main.item_cell_order.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/19.
 */
class GoodsInfoAdapter
@Inject constructor() : RecyclerView.Adapter<GoodsInfoAdapter.ViewHolder>() {

    internal var data: List<GoodsInfo> by Delegates.observable(ArrayList()) {
        _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsInfoAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_cell_order), viewType)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: GoodsInfoAdapter.ViewHolder, position: Int) {
        holder.tvValue1?.text = data[position].title
        holder.tvValue2?.text = data[position].value.toString() + data[position].unit
        holder.tvValue3?.text = data[position].subtitle
    }

    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var tvValue1: TextView? = null
        var tvValue2: TextView? = null
        var tvValue3: TextView? = null

        init {
            tvValue1 = itemView.tv_order_info_value1
            tvValue2 = itemView.tv_order_info_value2
            tvValue3 = itemView.tv_order_info_value3
        }
    }
}