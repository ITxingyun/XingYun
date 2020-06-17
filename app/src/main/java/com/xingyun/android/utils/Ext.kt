package com.xingyun.android.utils

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.xingyun.android.core.http.api.ApiResponse
import com.xingyun.android.core.http.api.Result
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

internal inline fun <reified T : Activity> Activity.start(
    requestCode: Int = -1,
    noinline configuration: Intent.() -> Unit = {},
    options: Bundle? = null
) {
    val intent = Intent(this, T::class.java)
    configuration(intent)
    if (requestCode != -1) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivity(intent, options)
    }
}