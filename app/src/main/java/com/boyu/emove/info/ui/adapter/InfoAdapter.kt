package com.boyu.emove.info.ui.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boyu.emove.R
import com.boyu.emove.extension.inflate
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
import kotlinx.android.synthetic.main.item_cell_header.view.*
import kotlinx.android.synthetic.main.item_cell_input.view.*
import kotlinx.android.synthetic.main.item_cell_select.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/16.
 */
class InfoAdapter
@Inject constructor() : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {

    internal var clickListener: (index: Int) -> Unit = {_ -> }
    internal var distanceChanged: (text: String, type: Int) -> Unit = {_,_ -> }


    internal var data: Pair<Moveout, Movein> by Delegates.observable(Pair(Moveout.empty(), Movein.empty())) {
        _, _, _ -> notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        var count = 10
        if (data.first.is_elevator == 0) {
            count++
        }
        if (data.second.is_elevator == 0) {
            count++
        }
        return count
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoAdapter.ViewHolder {
        when(viewType) {
            ItemType.HEADER.ordinal -> return ViewHolder(parent.inflate(R.layout.item_cell_header), viewType)
            ItemType.INPUTITEM.ordinal -> return ViewHolder(parent.inflate(R.layout.item_cell_input), viewType)
            ItemType.SELECTITEM.ordinal -> return ViewHolder(parent.inflate(R.layout.item_cell_select), viewType)
        }
        return ViewHolder(parent.inflate(R.layout.item_cell_header), viewType)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: InfoAdapter.ViewHolder, position: Int) {

        //楼层选择单独处理
        var offset_out = 0
        if(data.first.is_elevator == 0){
            offset_out++
        }
        var offset_in = 0
        if(data.second.is_elevator == 0){
            offset_in++
        }

        if(data.first.is_elevator == 0) {
            if(position == 3) {
                holder.tvTitle?.text = "选择楼层"
                holder.tvValue?.text = data.first.floor.toString() + "楼"
            }
        }

        if(data.second.is_elevator == 0) {
            if(data.first.is_elevator == 0) {
                if(position == 9) {
                    holder.tvTitle?.text = "选择楼层"
                    holder.tvValue?.text = data.second.floor.toString() + "楼"
                }
            }else {
                if(position == 8) {
                    holder.tvTitle?.text = "选择楼层"
                    holder.tvValue?.text = data.second.floor.toString() + "楼"
                }
            }
        }
        when(position) {
            0 ->  holder.tvHeader?.text = "请在下方填写您的搬出地址"
            1 ->  {
                holder.tvTitle?.let {
                    it.text = "搬出地址"
                }
                holder.tvValue?.let {
                    it.isFocusableInTouchMode = false
                    it.hint = "请在此填写您的搬出地址"
                    it.text = data.first.address
                }
            }
            2 -> {
                holder.tvTitle?.text = "有无电梯"
                holder.tvValue?.text = if(data.first.is_elevator == 0) "无" else "有"
            }

            3 + offset_out -> {
                holder.tvTitle?.text = "需要拼装"
                holder.tvValue?.text = if(data.first.is_handling == 0) "不需要" else "需要"
            }
            4 + offset_out  ->  {
                holder.tvTitle?.text = "搬运距离"
                holder.tvValue?.text = data.first.distance_meter.toString()
                holder.tvValue?.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        distanceChanged(p0.toString(), 0) // 0代表搬出距离
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
            }
            5 + offset_out  ->  holder.tvHeader?.text = "请在下方填写您的搬入地址"
            6 + offset_out  ->  {
                holder.tvTitle?.let {
                    it.text = "搬入地址"
                }
                holder.tvValue?.let {
                    it.isFocusableInTouchMode = false
                    it.hint = "请在此填写您的搬入地址"
                    it.text = data.second.address
                }
            }
            7 + offset_out  -> {
                holder.tvTitle?.text = "有无电梯"
                holder.tvValue?.text = if(data.second.is_elevator == 0) "无" else "有"
            }

            8 + offset_out + offset_in -> {
                holder.tvTitle?.text = "需要分拆"
                holder.tvValue?.text = if(data.second.is_handling == 0) "不需要" else "需要"
            }
            9 + offset_out + offset_in ->  {
                holder.tvTitle?.text = "搬运距离"
                holder.tvValue?.text =  data.second.distance_meter.toString()
                holder.tvValue?.addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(p0: Editable?) {
                        distanceChanged(p0.toString(), 1) // 1代表搬入距离
                    }

                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }

                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    }
                })
            }
        }
        holder.itemView.setOnClickListener {
           clickListener(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var offset_out = 0
        if(data.first.is_elevator == 0){
            offset_out++
        }
        var offset_in = 0
        if(data.second.is_elevator == 0){
            offset_in++
        }

        if(data.first.is_elevator == 0) {
            if(position == 3) {
                return ItemType.SELECTITEM.ordinal
            }
        }

        if(data.second.is_elevator == 0) {
            if(data.first.is_elevator == 0) {
                if(position == 9) {
                    return ItemType.SELECTITEM.ordinal
                }
            }else {
                if (position == 8) {
                    return ItemType.SELECTITEM.ordinal
                }
            }
        }

        when(position) {
            0 -> return ItemType.HEADER.ordinal
            1 -> return ItemType.INPUTITEM.ordinal
            2 -> return ItemType.SELECTITEM.ordinal
            3 + offset_out -> return ItemType.SELECTITEM.ordinal
            4 + offset_out -> return ItemType.INPUTITEM.ordinal
            5 + offset_out -> return ItemType.HEADER.ordinal
            6 + offset_out -> return ItemType.INPUTITEM.ordinal
            7 + offset_out -> return ItemType.SELECTITEM.ordinal
            8 + offset_out + offset_in -> return ItemType.SELECTITEM.ordinal
            9 + offset_out + offset_in -> return ItemType.INPUTITEM.ordinal

        }
        return ItemType.HEADER.ordinal
    }
    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var tvHeader: TextView? = null
        var tvTitle: TextView? = null
        var tvValue: TextView? = null

        init {
            when(viewType) {
                ItemType.HEADER.ordinal -> tvHeader = itemView.tv_text
                ItemType.INPUTITEM.ordinal -> {
                    tvTitle = itemView.tv_cell_input_title
                    tvValue = itemView.tv_cell_input_value
                }
                ItemType.SELECTITEM.ordinal -> {
                    tvTitle = itemView.tv_cell_select_title
                    tvValue = itemView.tv_cell_select_value
                }
            }
        }
    }

    enum class ItemType {
        HEADER, SELECTITEM, INPUTITEM
    }
}