package com.xingyun.android.model.source

import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.http.api.ResponseList
import com.xingyun.android.model.bean.Category
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.local.LocalArticleDataSource
import com.xingyun.android.model.source.remote.RemoteArticleDataSource
import javax.inject.Inject

class ArticleRepository @Inject constructor(
        private val remoteArticleDataSource: RemoteArticleDataSource,
        private val localArticleDataSource: LocalArticleDataSource) {

    suspend fun getQuestions(page: Int): Result<ResponseList<Article>> {
        return remoteArticleDataSource.getQuestions(page)
    }

    suspend fun getSquareArticles(page: Int): Result<ResponseList<Article>> {
        return remoteArticleDataSource.getSquareArticles(page)
    }

    suspend fun getLatestProject(page: Int): Result<ResponseList<Article>> {
        return remoteArticleDataSource.getLatestProject(page)
    }

    suspend fun getTopArticles(): Result<List<Article>> {
        return remoteArticleDataSource.getTopArticles()
    }

    suspend fun getRecommendArticles(page: Int): Result<ResponseList<Article>> {
        return remoteArticleDataSource.getRecommendArticles(page)
    }

    suspend fun getProjectList(page: Int, cid: Int): Result<ResponseList<Article>> {
        return remoteArticleDataSource.getProjectList(page, cid)
    }

    suspend fun getBlogArticles(cid: Int, page: Int): Result<ResponseList<Article>> {
        return remoteArticleDataSource.getBlogArticles(cid, page)
    }

    suspend fun getBlogCategory(): Result<List<Category>> {
        return remoteArticleDataSource.getBlogCategory()
    }

    suspend fun getProjectCategory(): Result<List<Category>> {
        return remoteArticleDataSource.getProjectCategory()
    }

    suspend fun getSystemCategory(): Result<List<Category>> {
        return remoteArticleDataSource.getSystemCategory()
    }

    suspend fun getSystemArticles(page: Int, cid: Int): Result<ResponseList<Article>> {
        return remoteArticleDataSource.getSystemArticles(page, cid)
    }

    suspend fun searchArticles(page: Int, key: String): Result<ResponseList<Article>> {
        return remoteArticleDataSource.searchArticles(page, key)
    }
}