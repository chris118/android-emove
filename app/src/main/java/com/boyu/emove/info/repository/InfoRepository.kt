package com.boyu.emove.info.repository

import com.boyu.emove.Login.entity.LoginResponse
import com.boyu.emove.Login.interactor.LoginInteractor
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.api.EmoveService
import com.boyu.emove.base.Repository.BaseRepository
import com.boyu.emove.info.entity.InfoResponse
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class InfoRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun getInfo(): BaseResponse<InfoResponse>? {
        return request(service.getInfo())
    }
}