package com.boyu.emove.Infoex.interactor

import com.boyu.emove.Infoex.entity.InfoEx
import com.boyu.emove.Infoex.repository.InfoexRepository
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.repository.InfoRepository
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