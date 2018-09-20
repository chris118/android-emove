package com.boyu.emove.goods.interactor

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.goods.Repository.GoodsRepository
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.repository.InfoRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class GetGoodsInteractor @Inject constructor(private val repository: GoodsRepository)
    : BaseInteractor<BaseInteractor.None, BaseResponse<Goods>>() {
    override suspend fun run(params: None): BaseResponse<Goods> {
        return repository.getGoods()!!
    }
}