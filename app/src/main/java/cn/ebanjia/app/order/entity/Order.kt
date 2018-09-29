package cn.ebanjia.app.order.entity

data class Order(
    val banjia_type_title: String,
    val user_name: String,
    val user_telephone: String,
    val user_note: String,
    val moving_time: String,
    val moveout_address: String,
    val moveout_floor: Int,
    val moveout_is_elevator: Int,
    val moveout_is_handling: Int,
    val moveout_distance_meter: Int,
    val movein_address: String,
    val movein_floor: Int,
    val movein_is_elevator: Int,
    val movein_is_handling: Int,
    val movein_distance_meter: Int,
    val is_invoice: Int,
    val fleet_name: String,
    val fleet_telephone: String,
    val fleet_address: String,
    val distance_kilometer: Double,
    val base_info: List<BaseInfo>,
    val goods_info: List<GoodsInfo>,
    val total_info: List<TotalInfo>
)

data class GoodsInfo(
    val type: String,
    val title: String,
    val subtitle: String,
    val value: Int,
    val unit: String
)

data class BaseInfo(
    val type: String,
    val title: String,
    val subtitle: String,
    val value: Float,
    val unit: String
)

data class TotalInfo(
    val type: String,
    val title: String,
    val subtitle: String,
    val value: Double,
    val unit: String
)

data class OrderSave(val order_id: Int)