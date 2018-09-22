package com.boyu.emove.login.entity

/**
 * Created by chrisw on 2018/9/13.
 */
data class LoginResponse(
    val uid: Int,
    val token: String,
    val nickname: String,
    val avatar: String,
    val group_id: Int
)
