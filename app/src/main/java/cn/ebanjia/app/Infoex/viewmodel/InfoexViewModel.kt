package cn.ebanjia.app.Infoex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.ebanjia.app.Infoex.entity.InfoEx
import cn.ebanjia.app.Infoex.entity.InfoExBody
import cn.ebanjia.app.Infoex.interactor.GetInfoexInteractor
import cn.ebanjia.app.Infoex.interactor.UpdateInfoExInteractor
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.interactor.UpdateInfoInteractor
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