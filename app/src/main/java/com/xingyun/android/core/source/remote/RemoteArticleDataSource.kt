package com.xingyun.android.core.source.remote

import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.http.api.WebService
import com.xingyun.android.core.model.ArticleList
import com.xingyun.android.utils.apiCall

class RemoteArticleDataSource(private val WebService: WebService) {

    suspend fun loadQuestionsAndAnswers(): Result<ArticleList> {
        return apiCall{ WebService.loadQuestionsAndAnswers() }
    }

    suspend fun loadSquareArticles(): Result<ArticleList> {
        return apiCall{ WebService.loadSquareArticles() }
    }




}