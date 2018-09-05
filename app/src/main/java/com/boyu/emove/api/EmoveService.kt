package com.boyu.emove.api

import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class EmoveService @Inject constructor(retrofit: Retrofit): ServiceApi {
    private val serviceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }

    override fun sendVerifyCode(mobile: String) = serviceApi.sendVerifyCode(mobile)
}