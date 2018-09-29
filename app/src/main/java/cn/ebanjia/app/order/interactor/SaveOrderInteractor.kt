package cn.ebanjia.app.order.interactor

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.goods.Repository.GoodsRepository
import cn.ebanjia.app.goods.entity.CartGood
import cn.ebanjia.app.goods.entity.CartGoodBody
import cn.ebanjia.app.goods.entity.Goods
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.repository.InfoRepository
import cn.ebanjia.app.order.entity.OrderSave
import cn.ebanjia.app.order.repository.OrderRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class SaveOrderInteractor @Inject constructor(private val repository: OrderRepository)
    : BaseInteractor<BaseInteractor.None, BaseResponse<OrderSave>>() {
    override suspend fun run(params: None): BaseResponse<OrderSave> {
        return repository.saveOrder()!!
    }
}