package com.boyu.emove.orderlist.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boyu.emove.R
import com.boyu.emove.extension.inflate
import com.boyu.emove.order.entity.BaseInfo
import com.boyu.emove.orderlist.entity.OrderList
import kotlinx.android.synthetic.main.fragment_order.view.*
import kotlinx.android.synthetic.main.item_cell_order.view.*
import kotlinx.android.synthetic.main.item_cell_order_list.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/19.
 */
class OrderListAdapter
@Inject constructor() : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {

    internal var data: List<OrderList> by Delegates.observable(ArrayList()) {
        _, _, _ ->
        notifyDataSetChanged()
    }

    internal var kanjiaClickListener: (orderId: Int) -> Unit = {_ -> }
    internal var orderClickListener: (orderId: Int) -> Unit = {_ -> }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_cell_order_list), viewType)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: OrderListAdapter.ViewHolder, position: Int) {
        holder.tvTitle?.text = "订单编号: " + data[position].order_sn
        holder.tvOut?.text = "搬出地址: " + data[position].moveout_address
        holder.tvIn?.text = "搬出地址: " + data[position].movein_address
        holder.tvTime?.text = data[position].moving_time + " " + data[position].time_slot_title
        when(data[position].order_status) {
            "wait" -> holder.tvStatus?.text = "未完成"
            "finish" -> holder.tvStatus?.text = "完成"
        }

        holder.btnDetail?.setOnClickListener {
            orderClickListener(data[position].order_id)
        }

        holder.btnKanjia?.setOnClickListener {
            kanjiaClickListener(data[position].order_id)
        }
    }

    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null
        var tvOut: TextView? = null
        var tvIn: TextView? = null
        var tvTime: TextView? = null
        var tvStatus: TextView? = null
        var btnKanjia: Button? = null
        var btnDetail: Button? = null


        init {
            tvTitle = itemView.tv_orderlist_title
            tvOut = itemView.tv_orderlist_out
            tvIn = itemView.tv_orderlist_in
            tvTime = itemView.tv_orderlist_time_value
            tvStatus = itemView.tv_orderlist_status
            btnKanjia = itemView.btn_orderlist_kanjia
            btnDetail = itemView.btn_orderlist_order
        }
    }
}