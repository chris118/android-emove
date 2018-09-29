package cn.ebanjia.app.info.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.baidu.mapapi.search.core.PoiInfo
import cn.ebanjia.app.R
import cn.ebanjia.app.extension.inflate
import kotlinx.android.synthetic.main.item_cell_address.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/19.
 */
class AddressAdapter
@Inject constructor() : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    internal var clickListener: (index: Int) -> Unit = {_ -> }

    internal var data: List<PoiInfo> by Delegates.observable(ArrayList()) {
        _, _, _ -> notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressAdapter.ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_cell_address), viewType)
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: AddressAdapter.ViewHolder, position: Int) {

        holder.tvValue?.text = data[position].address

        holder.tvValue?.setOnClickListener {
            clickListener(position)
        }
    }

    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        var tvValue: TextView? = null

        init {
            tvValue = itemView.tv_cell_address_value
        }
    }
}