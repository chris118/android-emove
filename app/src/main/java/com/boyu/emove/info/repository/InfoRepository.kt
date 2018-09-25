package com.boyu.emove.info.repository

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.api.EmoveService
import com.boyu.emove.base.Repository.BaseRepository
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
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