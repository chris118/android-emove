package cn.ebanjia.app.orderlist.repository

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.api.EmoveService
import cn.ebanjia.app.base.Repository.BaseRepository
import cn.ebanjia.app.orderlist.entity.OrderList
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class OrderListRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getOrderList(page: Int, order_status: String): BaseResponse<List<OrderList>>? {
        return request(service.getOrderList(page, order_status))
    }
}