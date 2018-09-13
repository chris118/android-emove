package com.boyu.emove.api

import com.boyu.emove.Login.entity.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by chrisw on 2018/9/5.
 */
interface ServiceApi {
    companion object {
        private const val SEND_VERIFY_CODE = "send/login-code"
        private const val SEND_VERIFY_CODE_PARAM = "username"
    }

    @GET(SEND_VERIFY_CODE)
    fun sendVerifyCode(@Query(SEND_VERIFY_CODE_PARAM)username: String): Call<LoginResponse>
}