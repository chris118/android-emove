package cn.ebanjia.app.info.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cn.ebanjia.app.api.BaseResponse
import cn.ebanjia.app.base.interactor.BaseInteractor
import cn.ebanjia.app.info.entity.Info
import cn.ebanjia.app.info.entity.Movein
import cn.ebanjia.app.info.entity.Moveout
import cn.ebanjia.app.info.interactor.GetInfoInteractor
import cn.ebanjia.app.info.interactor.UpdateInfoInteractor
import cn.ebanjia.app.info.interactor.SaveCompanyInteractor
import javax.inject.Inject

class InfoViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var getInfoInteractor: GetInfoInteractor

    @Inject
    lateinit var updateInfoInteractor: UpdateInfoInteractor

    @Inject
    lateinit var saveCompanyInteractor: SaveCompanyInteractor

    var getInfoResponse: MutableLiveData<BaseResponse<Info>> = MutableLiveData()
    var updateInfoResponse: MutableLiveData<BaseResponse<String>> = MutableLiveData()
    var saveCompanyResponse: MutableLiveData<BaseResponse<String>> = MutableLiveData()

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

    fun saveCompany(company_name: String, user_name: String, user_telephone: String, user_note: String) {
        saveCompanyInteractor(arrayOf(company_name, user_name, user_telephone, user_note)) {
            (this@InfoViewModel).saveCompanyResponse.value = it
        }
    }
}
