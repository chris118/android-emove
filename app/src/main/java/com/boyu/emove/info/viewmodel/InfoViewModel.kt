package com.boyu.emove.info.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.api.BaseResponse
import com.boyu.emove.base.interactor.BaseInteractor
import com.boyu.emove.info.entity.Info
import com.boyu.emove.info.entity.Movein
import com.boyu.emove.info.entity.Moveout
import com.boyu.emove.info.interactor.GetInfoInteractor
import com.boyu.emove.info.interactor.UpdateInfoInteractor
import javax.inject.Inject

class InfoViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getInfoInteractor: GetInfoInteractor

    @Inject
    lateinit var updateInfoInteractor: UpdateInfoInteractor

    var getInfoResponse: MutableLiveData<BaseResponse<Info>> = MutableLiveData()
    var updateInfoResponse: MutableLiveData<BaseResponse<String>> = MutableLiveData()

    fun getInfo() {
        getInfoInteractor(BaseInteractor.None()){
            (this@InfoViewModel).getInfoResponse.value = it
        }
    }

    fun updateInfo(moveout: Moveout, movein: Movein) {
        updateInfoInteractor(Pair(moveout, movein)) {
            (this@InfoViewModel).updateInfoResponse.value = it
        }
    }
}
