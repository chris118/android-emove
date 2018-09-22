package com.boyu.emove.Infoex.repository

import com.boyu.emove.Infoex.entity.InfoEx
import com.boyu.emove.Infoex.entity.InfoExBody
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
class InfoexRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getInfoex(): BaseResponse<InfoEx>? {
        return request(service.getInfoEx())
    }

    fun updateInfoex(body: InfoExBody): BaseResponse<String>? {
        return request(service.updateInfoEx(body))
    }
}