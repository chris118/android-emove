package com.boyu.emove.info.interactor

import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.repository.InfoRepository
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