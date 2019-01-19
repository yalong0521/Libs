package com.yalong.utils

import android.content.Context
import android.net.ConnectivityManager


@Suppress("DEPRECATION")
object NetworkUtils {
    /**
     * 判断WIFI是否连接
     */
    fun isWifiConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected && networkInfo.type == ConnectivityManager.TYPE_WIFI
    }
}