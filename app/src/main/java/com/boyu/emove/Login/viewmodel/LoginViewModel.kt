package com.boyu.emove.Login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.Login.entity.LoginResponse
import com.boyu.emove.Login.interactor.LoginInteractor
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/3.
 */
class LoginViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var interactor: LoginInteractor

    var sendVerifyCodeResponse: MutableLiveData<LoginResponse> = MutableLiveData()

    fun sendVerifyCode(mobile: String) {
        interactor(mobile) {
            (this@LoginViewModel).sendVerifyCodeResponse.value = it
        }
    }
}