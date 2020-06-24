package com.xingyun.android.ui.article

import java.io.Serializable

sealed class ArticleType : Serializable {
    object Recommend : ArticleType()
    object Square : ArticleType()
    object Question : ArticleType()
    object LatestProject : ArticleType()
    object Project : ArticleType()
    object Blog : ArticleType()
}