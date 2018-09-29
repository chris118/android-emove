package cn.ebanjia.app.goods.ui.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cn.ebanjia.app.R
import cn.ebanjia.app.extension.inflate
import cn.ebanjia.app.goods.entity.FirstCategory
import cn.ebanjia.app.goods.entity.SecondCategory
import kotlinx.android.synthetic.main.item_cell_category.view.*
import kotlinx.android.synthetic.main.item_cell_sub_category.view.*
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * Created by chrisw on 2018/9/19.
 */
class CategoryAdapter
@Inject constructor() : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var items = arrayListOf<CategoryItem>()
    internal var data: Pair<List<FirstCategory>, List<SecondCategory>> by Delegates.observable(Pair(ArrayList(), ArrayList())) {
        _, _, _ ->

        for(category in data.first) {
            items.add(CategoryItem(category.category_id, category.category_name, ItemType.CATEGORY))
            for(second_category in data.second) {
                if(second_category.parent_category_id == category.category_id){
                    items.add(CategoryItem(second_category.category_id, second_category.category_name, ItemType.SUBCATEGORY))
                }
            }
        }
        notifyDataSetChanged()
    }
    internal var selectGoodId: Int by Delegates.observable(0) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.ViewHolder {
        if (viewType == ItemType.CATEGORY.ordinal){
            return ViewHolder(parent.inflate(R.layout.item_cell_category), viewType)
        }
        return ViewHolder(parent.inflate(R.layout.item_cell_sub_category), viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].categoryType.ordinal
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: CategoryAdapter.ViewHolder, position: Int) {
        if(items[position].categoryType == ItemType.CATEGORY){
            holder.tvText?.text = items[position].categoryName
        }else {
            holder.tvText?.text = items[position].categoryName
            if (items[position].categoryId == selectGoodId){
                holder.llSelected?.visibility = View.VISIBLE
            }else{
                holder.llSelected?.visibility = View.GONE
            }
        }
    }

    class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {
        var tvText: TextView? = null
        var llSelected: LinearLayout? = null

        init {
            if (viewType == ItemType.CATEGORY.ordinal){
                tvText = itemView.tv_category_text
            }else {
                tvText = itemView.tv_sub_category_text
                llSelected = itemView.ll_select_category
            }
        }
    }

    data class CategoryItem(var categoryId: Int, var categoryName: String, var categoryType: ItemType)

    enum class ItemType {
        CATEGORY, SUBCATEGORY
    }
}