package com.xingyun.android.model.http.api

data class ResponseList<T>(
        val offset: Int,
        val size: Int,
        val total: Int,
        val pageCount: Int,
        val curPage: Int,
        val over: Boolean,
        val datas: List<T>
)