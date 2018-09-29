package cn.ebanjia.app.goods.Repository

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.api.EmoveService
import cn.ebanjia.app.base.Repository.BaseRepository
import cn.ebanjia.app.goods.entity.CartGood
import cn.ebanjia.app.goods.entity.CartGoodBody
import cn.ebanjia.app.goods.entity.Goods
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.entity.Movein
import cn.ebanjia.app.info.entity.Moveout
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class GoodsRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getGoods(): BaseResponse<Goods>? {
        return request(service.getGoods())
    }

    fun updateGoods(cart: CartGoodBody): BaseResponse<String>? {
        return request(service.updateGoods(cart))
    }
}