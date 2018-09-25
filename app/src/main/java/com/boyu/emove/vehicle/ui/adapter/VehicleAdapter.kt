package com.boyu.emove.vehicle.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boyu.emove.R
import com.boyu.emove.extension.inflate
import com.boyu.emove.goods.entity.CartGood
import com.boyu.emove.goods.entity.FirstCategory
import com.boyu.emove.goods.entity.SecondCategory
import com.boyu.emove.vehicle.entity.UsableFleet
import com.boyu.emove.vehicle.entity.VehicleInfo
import com.iarcuschin.simpleratingbar.SimpleRatingBar
import kotlinx.android.synthetic.main.item_cell_category.view.*
import kotlinx.android.synthetic.main.item_cell_good.view.*
import kotlinx.android.synthetic.main.item_cell_sub_category.view.*
import kotlinx.android.synthetic.main.item_cell_vehicle.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/19.
 */
class VehicleAdapter
@Inject constructor() : RecyclerView.Adapter<VehicleAdapter.ViewHolder>() {

    internal var data: List<UsableFleet> by Delegates.observable(ArrayList()) {
        _, _, _ ->
        notifyDataSetChanged()
    }

    internal var selectedFleet: Int by Delegates.observable(0) {
        _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (fleetId: Int) -> Unit = {_ -> }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_cell_vehicle), viewType)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: VehicleAdapter.ViewHolder, position: Int) {
        holder.tvTitle?.text = data[position].fleet_name
        holder.rateView?.rating = data[position].evaluate_star.toFloat()
        holder.tvComment?.text = "(" + data[position].evaluate_count.toString() + "次点评)"
        holder.tvAddress?.text = data[position].fleet_address + "\n距离您" + data[position].distance_kilometer + "米"
        holder.tvLeftCount?.text = "仅剩车次 " + data[position].remainder.toString()
        holder.tvKanjia?.text = "参与砍价活动, 该车队最低可砍至" + data[position].discount/10 + " 折"

        holder.cbSelect?.isChecked = data[position].fleet_id == selectedFleet

        holder.itemView.setOnClickListener {
            selectedFleet = data[position].fleet_id
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null
        var rateView: SimpleRatingBar? = null
        var tvComment: TextView? = null
        var tvAddress: TextView? = null
        var tvLeftCount: TextView? = null
        var tvKanjia: TextView? = null
        var cbSelect: CheckBox? = null

        init {
            tvTitle = itemView.tv_vehicle_title
            rateView = itemView.rate_view
            tvComment = itemView.tv_vehicle_comment
            tvAddress = itemView.tv_vehicle_address
            tvLeftCount = itemView.tv_vehicle_left
            tvKanjia = itemView.tv_kanjia
            cbSelect = itemView.cb_vehicle
        }
    }
}