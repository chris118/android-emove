package com.boyu.emove.api

/**
 * Created by chrisw on 2018/9/5.
 */
data class Response<T>(var code: Int, var msg: String, var result: T)