package com.boyu.emove.Login.repository

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.api.EmoveService
import com.boyu.emove.base.Repository.BaseRepository
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class LoginRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun sendVerifyCode(mobile: String): BaseResponse<String> {
        return request(service.sendVerifyCode(mobile)) {
            it
        }
    }
}