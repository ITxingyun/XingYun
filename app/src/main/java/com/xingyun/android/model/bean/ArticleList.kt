package com.xingyun.android.model.bean

import java.io.Serializable

data class ArticleList(
        val offset: Int,
        val size: Int,
        val total: Int,
        val pageCount: Int,
        val curPage: Int,
        val over: Boolean,
        val datas: List<Article>
) : Serializable