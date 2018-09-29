package cn.ebanjia.app.login.repository

import cn.ebanjia.app.login.entity.LoginResponse
import cn.ebanjia.app.login.interactor.LoginInteractor
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.api.EmoveService
import cn.ebanjia.app.base.Repository.BaseRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/5.
 */
class LoginRepository @Inject constructor(private val service: EmoveService): BaseRepository() {

    fun sendVerifyCode(username: String): BaseResponse<String>? {
        return request(service.sendVerifyCode(username))
    }

    fun login(params: LoginInteractor.Params): BaseResponse<LoginResponse>? {
        return request(service.login(params.username, params.code))
    }
}