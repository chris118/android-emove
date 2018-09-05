package com.boyu.emove.api

/**
 * Created by chrisw on 2018/9/5.
 */
import android.content.Context
import com.boyu.emove.extension.networkinfo

class NetworkHandler(private val context: Context) {
    val isconnected get() = context.networkinfo?.isConnected
}