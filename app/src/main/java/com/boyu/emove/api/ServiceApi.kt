package com.boyu.emove.api

import com.boyu.emove.Login.entity.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Created by chrisw on 2018/9/5.
 */
interface ServiceApi {
    companion object {
        private const val SEND_VERIFY_CODE = "send/login-code"
        private const val LOGIN = "code/login"
    }

    @GET(SEND_VERIFY_CODE)
    fun sendVerifyCode(@Query("username")username: String): Call<BaseResponse<String>>

    @POST(LOGIN)
    fun login(@Query("username")username: String,
              @Query("code")code: String,
              @Query("eappid")eappid: Int): Call<BaseResponse<LoginResponse>>
}