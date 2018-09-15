package com.boyu.emove.Login.repository

import com.boyu.emove.Login.entity.LoginResponse
import com.boyu.emove.Login.interactor.LoginInteractor
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.api.EmoveService
import com.boyu.emove.base.Repository.BaseRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class LoginRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun sendVerifyCode(username: String): BaseResponse<String>? {
        return request(service.sendVerifyCode(username))
    }

    fun login(params: LoginInteractor.Params): BaseResponse<LoginResponse>? {
        return request(service.login(params.username, params.code, params.eappid))
    }
}