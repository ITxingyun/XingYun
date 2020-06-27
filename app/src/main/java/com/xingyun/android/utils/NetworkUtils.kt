package com.xingyun.android.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.xingyun.android.common.app.WanAndroidApplication
import com.xingyun.android.common.exception.DataException
import com.xingyun.android.model.http.api.ApiResponse
import com.xingyun.android.model.http.api.Result
import java.io.IOException

suspend fun <T : Any> apiCall(call: suspend () -> ApiResponse<T>): Result<T> {
    return try {
        val response = call()
        if (response.errorCode == -1) {
            Result.Error(DataException(response.errorMsg))
        } else {
            Result.Success(response.data)
        }
    } catch (e: Exception) {
        Result.Error(IOException(e.message))
    }
}

fun isNetworkAvailable(): Boolean {
    val cm = WanAndroidApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}