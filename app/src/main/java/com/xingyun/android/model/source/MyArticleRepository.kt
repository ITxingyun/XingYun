package com.xingyun.android.model.source

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.model.source.db.AppDatabase
import com.xingyun.android.ui.home.RemoteArticleMediator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyArticleRepository @Inject constructor(
        private val database: AppDatabase,
        private val webService: WebService
) {

    fun getRecommendArticles() = Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = RemoteArticleMediator(ARTICLE_TYPE_RECOMMEND, database, webService)
    ) {
        database.articleDao().getArticlesByType(ARTICLE_TYPE_RECOMMEND)
    }.flow


    private companion object {
        const val ARTICLE_TYPE_RECOMMEND = "article_type_recommend"
    }

}