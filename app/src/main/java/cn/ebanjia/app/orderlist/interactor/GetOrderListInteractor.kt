package cn.ebanjia.app.orderlist.interactor

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.orderlist.entity.OrderList
import cn.ebanjia.app.orderlist.repository.OrderListRepository
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