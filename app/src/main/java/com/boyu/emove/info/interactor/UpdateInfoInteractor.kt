package com.boyu.emove.info.interactor

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
import com.boyu.emove.info.repository.InfoRepository
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