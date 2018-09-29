package cn.ebanjia.app.info.repository

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
class InfoRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getInfo(): BaseResponse<Info>? {
        return request(service.getInfo())
    }

    fun updateInfo(moveout: Moveout, movein: Movein): BaseResponse<String>? {
        var body = Info(moveout, movein)
        return request(service.updateInfo(body))
    }

    fun saveCompany(company_name: String, user_name: String, user_telephone: String, user_note: String): BaseResponse<String>? {
        return request(service.companySave(company_name, user_name, user_telephone, user_note))
    }
}