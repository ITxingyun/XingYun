package com.xingyun.android.model.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.source.db.AppDatabase
import com.xingyun.android.ui.home.RemoteArticleMediator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyArticleRepository @Inject constructor(
        private val database: AppDatabase,
        private val remoteMediator: RemoteArticleMediator
) {

    fun getRecommendArticles() = Pager<Int, Article>(
            config = PagingConfig(pageSize = 20),
            remoteMediator = remoteMediator
    ) {
        database.articleDao().articlePagingSource("query")
    }.flow

}