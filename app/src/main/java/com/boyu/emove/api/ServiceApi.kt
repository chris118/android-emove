package com.boyu.emove.api

import com.boyu.emove.Infoex.entity.InfoEx
import com.boyu.emove.Infoex.entity.InfoExBody
import com.boyu.emove.login.entity.LoginResponse
import com.boyu.emove.goods.entity.CartGoodBody
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.info.entity.Info
import com.boyu.emove.vehicle.entity.VehicleInfo
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
}