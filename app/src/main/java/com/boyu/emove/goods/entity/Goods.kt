package com.boyu.emove.goods.entity

/**
 * Created by chrisw on 2018/9/20.
 */
data class Goods(
    val first_category: List<FirstCategory>,
    val second_category: List<SecondCategory>,
    val all_goods: List<AllGood>,
    val cart_goods: List<CartGood>
)

data class FirstCategory(
    val category_id: Int,
    val category_name: String,
    val parent_category_id: Int
)

data class CartGood(
    val goods_id: Int,
    val goods_num: Int,
    val goods_name: String,
    val goods_cubage: Double,
    val parent_category_id: Int
)

data class SecondCategory(
    val category_id: Int,
    val category_name: String,
    val parent_category_id: Int
)

data class AllGood(
    val goods_id: Int,
    val goods_name: String,
    val goods_cubage: Double,
    val parent_category_id: Int
)