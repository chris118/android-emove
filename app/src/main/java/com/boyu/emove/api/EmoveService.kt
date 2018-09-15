package com.boyu.emove.api

import com.boyu.emove.Login.entity.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class EmoveService @Inject constructor(retrofit: Retrofit): ServiceApi {
    override fun login(username: String, code: String): Call<BaseResponse<LoginResponse>>
        = serviceApi.login(username, code)

    override fun sendVerifyCode(username: String) = serviceApi.sendVerifyCode(username)

    private val serviceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }
}