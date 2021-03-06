package cn.ebanjia.app.order.interactor

import cn.ebanjia.app.Infoex.entity.InfoEx
import cn.ebanjia.app.Infoex.repository.InfoexRepository
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.repository.InfoRepository
import cn.ebanjia.app.order.entity.Order
import cn.ebanjia.app.order.repository.OrderRepository
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