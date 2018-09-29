package cn.ebanjia.app.extension

/**
 * Created by chrisw on 2018/9/5.
 */

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

val Context.networkinfo
    get() = (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo