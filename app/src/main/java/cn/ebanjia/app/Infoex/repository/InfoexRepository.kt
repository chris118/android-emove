package cn.ebanjia.app.Infoex.repository

import cn.ebanjia.app.Infoex.entity.InfoEx
import cn.ebanjia.app.Infoex.entity.InfoExBody
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.api.EmoveService
import cn.ebanjia.app.base.Repository.BaseRepository
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.entity.Movein
import cn.ebanjia.app.info.entity.Moveout
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class InfoexRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getInfoex(): BaseResponse<InfoEx>? {
        return request(service.getInfoEx())
    }

    fun updateInfoex(body: InfoExBody): BaseResponse<String>? {
        return request(service.updateInfoEx(body))
    }
}