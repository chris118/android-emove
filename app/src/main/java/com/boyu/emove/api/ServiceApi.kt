package com.boyu.emove.api

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by chrisw on 2018/9/5.
 */
interface ServiceApi {
    companion object {
        private const val SEND_VERIFY_CODE = "send/login-code"
    }

    @GET(SEND_VERIFY_CODE)
    fun sendVerifyCode(mobile: String): Call<Response<String>>
}