package com.boyu.emove.order.interactor

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.goods.Repository.GoodsRepository
import com.boyu.emove.goods.entity.CartGood
import com.boyu.emove.goods.entity.CartGoodBody
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.repository.InfoRepository
import com.boyu.emove.order.entity.OrderSave
import com.boyu.emove.order.repository.OrderRepository
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