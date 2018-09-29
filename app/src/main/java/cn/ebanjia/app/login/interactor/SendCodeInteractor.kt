package cn.ebanjia.app.login.interactor

import cn.ebanjia.app.login.repository.LoginRepository
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
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
