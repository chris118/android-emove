package cn.ebanjia.app.goods.interactor

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.goods.Repository.GoodsRepository
import cn.ebanjia.app.goods.entity.CartGood
import cn.ebanjia.app.goods.entity.CartGoodBody
import cn.ebanjia.app.goods.entity.Goods
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.repository.InfoRepository
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