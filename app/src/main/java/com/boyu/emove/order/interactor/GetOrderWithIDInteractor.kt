package com.boyu.emove.order.interactor

import com.boyu.emove.Infoex.entity.InfoEx
import com.boyu.emove.Infoex.repository.InfoexRepository
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.repository.InfoRepository
import com.boyu.emove.order.entity.Order
import com.boyu.emove.order.repository.OrderRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class GetOrderWithIDInteractor @Inject constructor(private val repository: OrderRepository)
    : BaseInteractor<Int, BaseResponse<Order>>() {
    override suspend fun run(params: Int): BaseResponse<Order> {
        return repository.getOrderWithId(params)!!
    }
}