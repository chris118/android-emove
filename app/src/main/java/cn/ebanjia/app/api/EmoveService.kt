package cn.ebanjia.app.api

import cn.ebanjia.app.Infoex.entity.InfoExBody
import cn.ebanjia.app.login.entity.LoginResponse
import cn.ebanjia.app.goods.entity.CartGoodBody
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.order.entity.Order
import cn.ebanjia.app.order.entity.OrderSave
import cn.ebanjia.app.orderlist.entity.OrderList
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

    override fun companySave(company_name: String, user_name: String, user_telephone: String, user_note: String): Call<BaseResponse<String>>
            = serviceApi.companySave(company_name,user_name, user_telephone,user_note)
}