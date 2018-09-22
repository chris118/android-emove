package com.boyu.emove.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.login.entity.LoginResponse
import com.boyu.emove.login.interactor.LoginInteractor
import com.boyu.emove.login.interactor.SendCodeInteractor
import com.boyu.emove.api.BaseResponse
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/3.
 */
class LoginViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var sendCode: SendCodeInteractor

    @Inject
    lateinit var login: LoginInteractor

    var sendVerifyCodeResponse: MutableLiveData<BaseResponse<String>> = MutableLiveData()
    var loginResponse: MutableLiveData<BaseResponse<LoginResponse>> = MutableLiveData()

    fun sendVerifyCode(mobile: String) {
        sendCode(mobile) {
            (this@LoginViewModel).sendVerifyCodeResponse.value = it
        }
    }

    fun login(params: LoginInteractor.Params) {
        login(params) {
            (this@LoginViewModel).loginResponse.value = it
        }
    }
}