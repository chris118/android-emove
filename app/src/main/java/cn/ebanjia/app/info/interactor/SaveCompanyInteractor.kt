package cn.ebanjia.app.info.interactor

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.repository.InfoRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class SaveCompanyInteractor @Inject constructor(private val repository: InfoRepository)
    : BaseInteractor<Array<String>, BaseResponse<String>>() {
    override suspend fun run(params: Array<String>): BaseResponse<String> {
        return repository.saveCompany(params[0], params[1],params[2],params[3])!!
    }
}