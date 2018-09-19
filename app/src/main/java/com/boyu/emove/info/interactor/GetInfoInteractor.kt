package com.boyu.emove.info.interactor

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.repository.InfoRepository
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