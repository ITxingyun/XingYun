package com.xingyun.android.common.ext

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