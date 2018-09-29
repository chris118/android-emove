package cn.ebanjia.app.login.interactor

import cn.ebanjia.app.login.entity.LoginResponse
import cn.ebanjia.app.login.repository.LoginRepository
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
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