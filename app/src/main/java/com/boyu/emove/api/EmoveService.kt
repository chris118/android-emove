package com.boyu.emove.api

import com.boyu.emove.Infoex.entity.InfoExBody
import com.boyu.emove.login.entity.LoginResponse
import com.boyu.emove.goods.entity.CartGoodBody
import com.boyu.emove.info.entity.Info
import com.boyu.emove.order.entity.Order
import com.boyu.emove.order.entity.OrderSave
import com.boyu.emove.orderlist.entity.OrderList
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class EmoveService @Inject constructor(retrofit: Retrofit): ServiceApi {
    private val serviceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }

    override fun login(username: String, code: String): Call<BaseResponse<LoginResponse>>
        = serviceApi.login(username, code)

    override fun sendVerifyCode(username: String) = serviceApi.sendVerifyCode(username)

    override fun getInfo() = serviceApi.getInfo()

    override fun updateInfo(body: Info) = serviceApi.updateInfo(body)

    override fun getGoods() = serviceApi.getGoods()

    override fun updateGoods(body: CartGoodBody) = serviceApi.updateGoods(body)

    override fun getInfoEx() = serviceApi.getInfoEx()

    override fun updateInfoEx(body: InfoExBody) = serviceApi.updateInfoEx(body)

    override fun getVehicleInfo(order_by_field: String) = serviceApi.getVehicleInfo(order_by_field)

    override fun updateVehicleInfo(body: Int): Call<BaseResponse<String>> = serviceApi.updateVehicleInfo(body)

    override fun getOrder(): Call<BaseResponse<Order>> = serviceApi.getOrder()

    override fun saveOrder(): Call<BaseResponse<OrderSave>> = serviceApi.saveOrder()

    override fun getOrderList(page: Int, order_status: String): Call<BaseResponse<List<OrderList>>> = serviceApi.getOrderList(page, order_status)

    override fun getOrderWithId(order_id: Int): Call<BaseResponse<Order>> = serviceApi.getOrderWithId(order_id)
}