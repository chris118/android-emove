package com.boyu.emove.orderlist.repository

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.api.EmoveService
import com.boyu.emove.base.Repository.BaseRepository
import com.boyu.emove.orderlist.entity.OrderList
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class OrderListRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getOrderList(page: Int, order_status: String): BaseResponse<List<OrderList>>? {
        return request(service.getOrderList(page, order_status))
    }
}