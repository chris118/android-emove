package com.boyu.emove.Infoex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.Infoex.entity.InfoEx
import com.boyu.emove.Infoex.entity.InfoExBody
import com.boyu.emove.Infoex.interactor.GetInfoexInteractor
import com.boyu.emove.Infoex.interactor.UpdateInfoExInteractor
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.interactor.UpdateInfoInteractor
import javax.inject.Inject

class InfoexViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getInfoexInteractor: GetInfoexInteractor

    var getInfoexResponse: MutableLiveData<BaseResponse<InfoEx>> = MutableLiveData()

    fun getInfoex() {
        getInfoexInteractor(BaseInteractor.None()) {
            (this@InfoexViewModel).getInfoexResponse.value = it
        }
    }

    @Inject
    lateinit var updateInfoExInteractor: UpdateInfoExInteractor

    var updateInfoExResponse: MutableLiveData<BaseResponse<String>> = MutableLiveData()

    fun updateInfoex(body: InfoExBody) {
        updateInfoExInteractor(body) {
            (this@InfoexViewModel).updateInfoExResponse.value = it
        }
    }
}