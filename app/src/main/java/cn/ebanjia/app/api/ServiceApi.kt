package cn.ebanjia.app.api

import cn.ebanjia.app.Infoex.entity.InfoEx
import cn.ebanjia.app.Infoex.entity.InfoExBody
import cn.ebanjia.app.login.entity.LoginResponse
import cn.ebanjia.app.goods.entity.CartGoodBody
import cn.ebanjia.app.goods.entity.Goods
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.order.entity.Order
import cn.ebanjia.app.order.entity.OrderSave
import cn.ebanjia.app.orderlist.entity.OrderList
import cn.ebanjia.app.vehicle.entity.VehicleInfo
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by chrisw on 2018/9/5.
 */
interface ServiceApi {
    companion object {
        private const val SEND_VERIFY_CODE = "send/login-code"
        private const val LOGIN = "code/login"
        private const val GETINFO = "cart/address"
        private const val GOODS = "/cart/goods"
        private const val INFOEX = "/cart/time"
        private const val VEHICLE = "/cart/fleet"
        private const val ORDER = "/cart/finish"
        private const val GETORDER = "/get/order"
        private const val ORDERSAVE = "/order/save"
        private const val ORDERLIST = "/get/orders"
        private const val COMPANY = "order/company-save"
    }

    @GET(SEND_VERIFY_CODE)
    fun sendVerifyCode(@Query("username")username: String): Call<BaseResponse<String>>

    @POST(LOGIN)
    fun login(@Query("username")username: String,
              @Query("code")code: String): Call<BaseResponse<LoginResponse>>

    @GET(GETINFO)
    fun getInfo(): Call<BaseResponse<Info>>

    @POST(GETINFO)
    fun updateInfo(@Body body: Info): Call<BaseResponse<String>>


    @GET(GOODS)
    fun getGoods(): Call<BaseResponse<Goods>>

    @POST(GOODS)
    fun updateGoods(@Body body: CartGoodBody): Call<BaseResponse<String>>

    @GET(INFOEX)
    fun getInfoEx(): Call<BaseResponse<InfoEx>>

    @POST(INFOEX)
    fun updateInfoEx(@Body body: InfoExBody): Call<BaseResponse<String>>

    @GET(VEHICLE)
    fun getVehicleInfo(@Query("order_by_field")order_by_field: String): Call<BaseResponse<VehicleInfo>>

    @POST(VEHICLE)
    fun updateVehicleInfo(@Query("fleet_id") fleet_id: Int): Call<BaseResponse<String>>

    @GET(ORDER)
    fun getOrder(): Call<BaseResponse<Order>>

    @POST(ORDERSAVE)
    fun saveOrder(): Call<BaseResponse<OrderSave>>

    @GET(ORDERLIST)
    fun getOrderList(@Query("page") page: Int, @Query("order_status") order_status: String): Call<BaseResponse<List<OrderList>>>

    @GET(GETORDER)
    fun getOrderWithId(@Query("order_id")order_id: Int): Call<BaseResponse<Order>>

    @POST(COMPANY)
    fun companySave(@Query("company_name") company_name: String,
                    @Query("user_name") user_name: String,
                    @Query("user_telephone") user_telephone: String,
                    @Query("user_note") user_note: String)
            : Call<BaseResponse<String>>

}