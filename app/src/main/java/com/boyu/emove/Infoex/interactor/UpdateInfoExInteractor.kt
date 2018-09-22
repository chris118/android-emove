package com.boyu.emove.Infoex.interactor

import com.boyu.emove.Infoex.entity.InfoExBody
import com.boyu.emove.Infoex.repository.InfoexRepository
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
import com.boyu.emove.info.repository.InfoRepository
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