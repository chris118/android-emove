package cn.ebanjia.app.order.repository

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.api.EmoveService
import cn.ebanjia.app.base.Repository.BaseRepository
import cn.ebanjia.app.order.entity.Order
import cn.ebanjia.app.order.entity.OrderSave
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class OrderRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getOrder(): BaseResponse<Order>? {
        return request(service.getOrder())
    }

    fun saveOrder(): BaseResponse<OrderSave>? {
        return request(service.saveOrder())
    }

    fun getOrderWithId(order_id: Int): BaseResponse<Order>? {
        return request(service.getOrderWithId(order_id))
    }
}