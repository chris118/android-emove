package com.boyu.emove.api

import com.boyu.emove.Login.entity.LoginResponse
import com.boyu.emove.goods.entity.Goods
import com.boyu.emove.info.entity.Info
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class EmoveService @Inject constructor(retrofit: Retrofit): ServiceApi {

    private val serviceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }

    override fun login(username: String, code: String): Call<BaseResponse<LoginResponse>>
        = serviceApi.login(username, code)

    override fun sendVerifyCode(username: String) = serviceApi.sendVerifyCode(username)

    override fun getInfo() = serviceApi.getInfo()

    override fun updateInfo(body: Info) = serviceApi.updateInfo(body)

    override fun getGoods() = serviceApi.getGoods()

}