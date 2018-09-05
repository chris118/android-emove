package com.boyu.emove.Login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.boyu.emove.Login.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by chrisw on 2018/9/3.
 */
class LoginViewModel @Inject constructor(repository: LoginRepository): ViewModel() {
    var sendVerifyCode: LiveData<Response<String>> = MutableLiveData()

    fun sendVerifyCode(mobile: String) {

    }
}