package cn.ebanjia.app.Infoex.interactor

import cn.ebanjia.app.Infoex.entity.InfoEx
import cn.ebanjia.app.Infoex.repository.InfoexRepository
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.repository.InfoRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/15.
 */
class GetInfoexInteractor @Inject constructor(private val repository: InfoexRepository)
    : BaseInteractor<BaseInteractor.None, BaseResponse<InfoEx>>() {
    override suspend fun run(params: None): BaseResponse<InfoEx> {
        return repository.getInfoex()!!
    }
}