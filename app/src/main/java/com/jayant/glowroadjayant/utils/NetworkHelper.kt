package com.jayant.glowroadjayant.utils

import android.content.Context
import android.net.ConnectivityManager

object NetworkHelper {
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return try {
            val info = connectivityManager.activeNetworkInfo
            info != null && info.isConnectedOrConnecting
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}