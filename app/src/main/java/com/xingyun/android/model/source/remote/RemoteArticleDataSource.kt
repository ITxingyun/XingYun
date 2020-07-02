package com.xingyun.android.model.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.http.api.ResponseList
import com.xingyun.android.model.bean.Category
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.ui.home.ArticlePageSource
import com.xingyun.android.utils.apiCall

class RemoteArticleDataSource(private val webService: WebService) {

    suspend fun getTopArticles(): Result<List<Article>> {
        return apiCall { webService.getTopArticles() }
    }

    suspend fun getRecommendArticles(page: Int): Result<ResponseList<Article>> {
        return apiCall { webService.getRecommendArticles(page) }
    }

    suspend fun getQuestions(page: Int): Result<ResponseList<Article>> {
        return apiCall { webService.getQuestions(page) }
    }

    suspend fun getSquareArticles(page: Int): Result<ResponseList<Article>> {
        return apiCall { webService.getSquareArticles(page) }
    }

    suspend fun getLatestProject(page: Int): Result<ResponseList<Article>> {
        return apiCall { webService.getLatestProject(page) }
    }

    suspend fun getProjectList(page: Int, cid: Int): Result<ResponseList<Article>> {
        return apiCall { webService.getProjectList(page, cid) }
    }

    suspend fun getBlogArticles(cid: Int, page: Int): Result<ResponseList<Article>> {
        return apiCall { webService.getBlogArticles(cid, page) }
    }

    suspend fun getBlogCategory(): Result<List<Category>> {
        return apiCall { webService.getBlogCategory() }
    }

    suspend fun getProjectCategory(): Result<List<Category>> {
        return apiCall { webService.getProjectCategory() }
    }

    suspend fun getSystemCategory(): Result<List<Category>> {
        return apiCall { webService.getSystemCategory() }
    }

    suspend fun getSystemArticles(page: Int, cid: Int): Result<ResponseList<Article>> {
        return apiCall { webService.getSystemTypeArticles(page, cid) }
    }

    suspend fun searchArticles(page: Int, key: String): Result<ResponseList<Article>> {
        return apiCall { webService.searchArticles(page, key) }
    }

    val articlesFlow = Pager(PagingConfig(pageSize = 10)){
        ArticlePageSource(1, webService)
    }.flow
}