package com.xingyun.android.core.http.api

import com.xingyun.android.core.http.api.Status.SUCCESS
import com.xingyun.android.core.http.api.Status.ERROR
import com.xingyun.android.core.http.api.Status.LOADING

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(error: Throwable): Resource<T> {
            return Resource(ERROR, null, error)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}