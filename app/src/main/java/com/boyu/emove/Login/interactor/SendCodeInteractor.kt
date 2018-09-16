package com.boyu.emove.Login.interactor

import com.boyu.emove.Login.entity.LoginResponse
import com.boyu.emove.Login.repository.LoginRepository
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/6.
 */
class SendCodeInteractor @Inject constructor(private val repository: LoginRepository)
    : BaseInteractor<String, BaseResponse<String>>() {
    override suspend fun run(params: String): BaseResponse<String> {
        return repository.sendVerifyCode(params)!!
    }
}