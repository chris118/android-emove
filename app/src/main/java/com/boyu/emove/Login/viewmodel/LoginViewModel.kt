package com.boyu.emove.Login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.Login.entity.LoginResponse
import com.boyu.emove.Login.interactor.LoginInteractor
import com.boyu.emove.Login.repository.LoginRepository
import com.boyu.emove.api.BaseResponse
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/3.
 */
class LoginViewModel @Inject constructor(private val interactor: LoginInteractor): ViewModel() {
    var sendVerifyCodeResponse: MutableLiveData<LoginResponse> = MutableLiveData()

    fun sendVerifyCode(mobile: String) {
        interactor(mobile) {
            (this@LoginViewModel).sendVerifyCodeResponse.value = it
        }
    }
}