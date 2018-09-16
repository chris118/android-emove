package com.boyu.emove.info.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.boyu.emove.R
import com.boyu.emove.extension.inflate
import kotlinx.android.synthetic.main.item_cell_header.view.*
import kotlinx.android.synthetic.main.item_cell_input.view.*
import kotlinx.android.synthetic.main.item_cell_select.view.*
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/16.
 */
class InfoAdapter
@Inject constructor() : RecyclerView.Adapter<InfoAdapter.ViewHolder>() {
    override fun getItemCount(): Int {
        return 12
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
        when(position) {
            0 ->  holder.tvHeader?.text = "请在下方填写您的搬出地址"
            1 ->  {
                holder.tvTitle?.let {
                    it.text = "搬出地址"
                }
                holder.tvValue?.let {
                    it.isFocusableInTouchMode = false
                    it.hint = "请在此填写您的搬出地址"
                }
            }
            2 -> {
                holder.tvTitle?.text = "有无电梯"
                holder.tvValue?.text = "有"
            }
            3 -> {
                holder.tvTitle?.text = "选择楼层"
                holder.tvValue?.text = "1"
            }
            4 -> {
                holder.tvTitle?.text = "需要拼装"
                holder.tvValue?.text = "需要"
            }
            5 ->  {
                holder.tvTitle?.text = "搬运距离"
                holder.tvValue?.text = "25"
            }
            6 ->  holder.tvHeader?.text = "请在下方填写您的搬入地址"
            7 ->  {
                holder.tvTitle?.let {
                    it.text = "搬入地址"
                }
                holder.tvValue?.let {
                    it.isFocusableInTouchMode = false
                    it.hint = "请在此填写您的搬入地址"
                }
            }
            8 -> {
                holder.tvTitle?.text = "有无电梯"
                holder.tvValue?.text = "有"
            }
            9 -> {
                holder.tvTitle?.text = "选择楼层"
                holder.tvValue?.text = "1"
            }
            10 -> {
                holder.tvTitle?.text = "需要分拆"
                holder.tvValue?.text = "需要"
            }
            11 ->  {
                holder.tvTitle?.text = "搬运距离"
                holder.tvValue?.text = "25"
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when(position) {
            0 -> return ItemType.HEADER.ordinal
            1 -> return ItemType.INPUTITEM.ordinal
            2,3,4 -> return ItemType.SELECTITEM.ordinal
            5 -> return ItemType.INPUTITEM.ordinal
            6 -> return ItemType.HEADER.ordinal
            7 -> return ItemType.INPUTITEM.ordinal
            8,9,10 -> return ItemType.SELECTITEM.ordinal
            11 -> return ItemType.INPUTITEM.ordinal

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