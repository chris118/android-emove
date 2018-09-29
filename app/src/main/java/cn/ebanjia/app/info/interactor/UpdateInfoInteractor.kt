package cn.ebanjia.app.info.interactor

import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Movein
import cn.ebanjia.app.info.entity.Moveout
import cn.ebanjia.app.info.repository.InfoRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/19.
 */
class UpdateInfoInteractor @Inject constructor(private val repository: InfoRepository)
    : BaseInteractor<Pair<Moveout, Movein>, BaseResponse<String>>() {
    override suspend fun run(params: Pair<Moveout, Movein>): BaseResponse<String> {
        return repository.updateInfo(params.first, params.second)!!
    }
}