package com.boyu.emove.Login.interactor

import com.boyu.emove.Login.entity.LoginResponse
import com.boyu.emove.Login.repository.LoginRepository
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class LoginInteractor @Inject constructor(private val repository: LoginRepository)
    : BaseInteractor<LoginInteractor.Params, BaseResponse<LoginResponse>>() {
    override suspend fun run(params: Params): BaseResponse<LoginResponse> {
        return repository.login(params)!!
    }

    data class Params(val username: String, val code: String)
}