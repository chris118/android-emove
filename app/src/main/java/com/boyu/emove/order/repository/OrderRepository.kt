package com.boyu.emove.order.repository

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.api.EmoveService
import com.boyu.emove.base.Repository.BaseRepository
import com.boyu.emove.order.entity.Order
import com.boyu.emove.order.entity.OrderSave
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
}