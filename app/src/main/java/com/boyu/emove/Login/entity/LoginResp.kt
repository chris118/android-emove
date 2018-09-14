package com.boyu.emove.Login.entity

/**
 * Created by chrisw on 2018/9/13.
 */
data class LoginResponse(var code: Int, var msg: String, var result: String) {
    companion object {
        fun empty() = LoginResponse(-1, "", "")
    }
}