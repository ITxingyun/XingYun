package com.xingyun.android.core.source

import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.model.ArticleList
import com.xingyun.android.core.model.Banner
import com.xingyun.android.core.source.local.LocalArticleDataSource
import com.xingyun.android.core.source.remote.RemoteArticleDataSource
import javax.inject.Inject

class ArticleRepository @Inject constructor(
        private val remoteArticleDataSource: RemoteArticleDataSource,
        private val localArticleDataSource: LocalArticleDataSource) {

    suspend fun loadQuestionsAndAnswers(): Result<ArticleList> {
        return remoteArticleDataSource.loadQuestionsAndAnswers()
    }

    suspend fun getTopArticles(): Result<List<Article>> {
        return remoteArticleDataSource.getTopArticles()
    }

    suspend fun getHomeArticles(page: Int): Result<ArticleList> {
        return remoteArticleDataSource.getHomeArticles(page)
    }

    suspend fun getBanners(): Result<List<Banner>> {
        return remoteArticleDataSource.loadBanner()
    }

}