package cn.ebanjia.app.info.interactor

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.repository.InfoRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class GetInfoInteractor @Inject constructor(private val repository: InfoRepository)
    : BaseInteractor<BaseInteractor.None, BaseResponse<Info>>() {
    override suspend fun run(params: None): BaseResponse<Info> {
        return repository.getInfo()!!
    }
}