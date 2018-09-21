package com.boyu.emove.api

import com.boyu.emove.Login.entity.LoginResponse
import com.boyu.emove.goods.entity.CartGood
import com.boyu.emove.goods.entity.CartGoodBody
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.info.entity.Info
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
}