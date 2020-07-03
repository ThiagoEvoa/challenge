package com.example.codechallenge.util

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.fragment.app.FragmentActivity

class InternetUtil {
    fun isDeviceConnected(context: FragmentActivity): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.activeNetwork ?: return false
        } else {
            connectivityManager.activeNetworkInfo ?: return false
        }
        return true
    }
}