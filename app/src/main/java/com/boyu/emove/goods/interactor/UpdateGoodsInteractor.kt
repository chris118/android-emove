package com.boyu.emove.goods.interactor

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.goods.Repository.GoodsRepository
import com.boyu.emove.goods.entity.CartGood
import com.boyu.emove.goods.entity.CartGoodBody
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.repository.InfoRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class UpdateGoodsInteractor @Inject constructor(private val repository: GoodsRepository)
    : BaseInteractor<CartGoodBody, BaseResponse<String>>() {
    override suspend fun run(params: CartGoodBody): BaseResponse<String> {
        return repository.updateGoods(params)!!
    }
}