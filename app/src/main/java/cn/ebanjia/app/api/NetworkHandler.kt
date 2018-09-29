package cn.ebanjia.app.api

/**
 * Created by chrisw on 2018/9/5.
 */
import android.content.Context
import cn.ebanjia.app.extension.networkinfo

class NetworkHandler(private val context: Context) {
    val isconnected get() = context.networkinfo?.isConnected
}