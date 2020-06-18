package com.xingyun.android.core.source.remote

import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.http.api.WebService
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.model.ArticleList
import com.xingyun.android.core.model.Banner
import com.xingyun.android.utils.apiCall

class RemoteArticleDataSource(private val WebService: WebService) {

    suspend fun loadBanner(): Result<List<Banner>> {
        return apiCall{ WebService.getBanner() }
    }

    suspend fun getTopArticles(): Result<List<Article>> {
        return apiCall{ WebService.getTopArticles() }
    }

    suspend fun getHomeArticles(page: Int): Result<ArticleList> {
        return apiCall{ WebService.getHomeArticles(page) }
    }

    suspend fun loadQuestionsAndAnswers(): Result<ArticleList> {
        return apiCall{ WebService.loadQuestionsAndAnswers() }
    }

    suspend fun loadSquareArticles(): Result<ArticleList> {
        return apiCall{ WebService.loadSquareArticles() }
    }




}