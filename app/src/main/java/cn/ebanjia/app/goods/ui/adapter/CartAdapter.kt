package cn.ebanjia.app.goods.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.ebanjia.app.R
import cn.ebanjia.app.extension.inflate
import cn.ebanjia.app.goods.entity.CartGood
import cn.ebanjia.app.goods.entity.FirstCategory
import cn.ebanjia.app.goods.entity.SecondCategory
import kotlinx.android.synthetic.main.item_cell_category.view.*
import kotlinx.android.synthetic.main.item_cell_good.view.*
import kotlinx.android.synthetic.main.item_cell_sub_category.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/19.
 */
class CartAdapter
@Inject constructor() : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    internal var data: List<CartGood> by Delegates.observable(ArrayList()) {
        _, _, _ ->
        notifyDataSetChanged()
    }

    internal var plusClickListener: (goodId: Int) -> Unit = {_ -> }
    internal var minusClickListener: (goodId: Int) -> Unit = {_ -> }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_cell_good), viewType)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.tvText?.text = data[position].goods_name
        holder.tvNumber?.text = data[position].goods_num.toString()
        if(data[position].goods_num == 0){
            holder.btnMinus?.visibility = View.INVISIBLE
            holder.tvNumber?.visibility = View.INVISIBLE
        }else {
            holder.btnMinus?.visibility = View.VISIBLE
            holder.tvNumber?.visibility = View.VISIBLE
        }

        holder.btnPlus?.setOnClickListener  {
            plusClickListener(data[position].goods_id)
        }

        holder.btnMinus?.setOnClickListener  {
            minusClickListener(data[position].goods_id)
        }
    }

    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var tvText: TextView? = null
        var btnPlus: Button? = null
        var btnMinus: Button? = null
        var tvNumber: TextView? = null

        init {
            btnPlus = itemView.btn_add
            btnMinus = itemView.btn_minus
            tvText = itemView.tv_goods_text
            tvNumber = itemView.tv_good_number
        }
    }
}