package com.xingyun.android.utils

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