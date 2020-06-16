package com.xingyun.android.core.http.api

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class ResponseBody<T>(val data: T, val errorCode: Int, val errorMsg: String)

@JsonClass(generateAdapter = true)
class ListResponseBody<D>(
    val curPage: Int,
    val datas: D,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)