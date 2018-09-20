package com.boyu.emove.goods.Repository

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.api.EmoveService
import com.boyu.emove.base.Repository.BaseRepository
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class GoodsRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getGoods(): BaseResponse<Goods>? {
        return request(service.getGoods())
    }
}