package com.jayant.glowroadjayant.utils

import android.content.Context
import android.net.ConnectivityManager

object Validator {
    fun isPageNumberValid(page: Int): Boolean {
        return page != 0
    }
}