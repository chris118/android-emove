package cn.ebanjia.app.Infoex.interactor

import cn.ebanjia.app.Infoex.entity.InfoExBody
import cn.ebanjia.app.Infoex.repository.InfoexRepository
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Movein
import cn.ebanjia.app.info.entity.Moveout
import cn.ebanjia.app.info.repository.InfoRepository
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/19.
 */
class UpdateInfoExInteractor @Inject constructor(private val repository: InfoexRepository)
    : BaseInteractor<InfoExBody, BaseResponse<String>>() {
    override suspend fun run(params: InfoExBody): BaseResponse<String> {
        return repository.updateInfoex(params)!!
    }
}