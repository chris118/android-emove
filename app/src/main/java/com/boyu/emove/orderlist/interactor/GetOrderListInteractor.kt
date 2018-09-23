package com.boyu.emove.orderlist.interactor

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.orderlist.entity.OrderList
import com.boyu.emove.orderlist.repository.OrderListRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class GetOrderListInteractor @Inject constructor(private val repository: OrderListRepository)
    : BaseInteractor<Pair<Int, String>, BaseResponse<List<OrderList>>>() {
    override suspend fun run(params: Pair<Int, String>): BaseResponse<List<OrderList>> {
        return repository.getOrderList(params.first, params.second)!!
    }
}