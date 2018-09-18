package com.boyu.emove.info.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.InfoResponse
import com.boyu.emove.info.interactor.GetInfoInteractor
import javax.inject.Inject

class InfoViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getInfoInteractor: GetInfoInteractor

    var getInfoResponse: MutableLiveData<BaseResponse<InfoResponse>> = MutableLiveData()

    fun getInfo() {
        getInfoInteractor(BaseInteractor.None()){
            (this@InfoViewModel).getInfoResponse.value = it
        }
    }
}
