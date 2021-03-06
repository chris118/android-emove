package cn.ebanjia.app.order.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.ebanjia.app.R
import cn.ebanjia.app.extension.inflate
import cn.ebanjia.app.order.entity.BaseInfo
import cn.ebanjia.app.order.entity.TotalInfo
import kotlinx.android.synthetic.main.item_cell_order.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/19.
 */
class TotalInfoAdapter
@Inject constructor() : RecyclerView.Adapter<TotalInfoAdapter.ViewHolder>() {

    internal var data: List<TotalInfo> by Delegates.observable(ArrayList()) {
        _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TotalInfoAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_cell_order), viewType)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: TotalInfoAdapter.ViewHolder, position: Int) {
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