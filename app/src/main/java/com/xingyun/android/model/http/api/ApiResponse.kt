package com.xingyun.android.model.http.api

data class ApiResponse<T>(val data: T, val errorCode: Int, val errorMsg: String)

data class ListResponseBody<D>(
    val curPage: Int,
    val datas: D,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)