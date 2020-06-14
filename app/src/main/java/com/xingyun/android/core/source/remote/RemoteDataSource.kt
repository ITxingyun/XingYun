package com.xingyun.android.core.source.remote

import com.xingyun.android.core.model.Article
import com.xingyun.android.core.http.XYRetrofitService

class RemoteDataSource(
    private val XYRetrofitService: XYRetrofitService
)  {

    suspend fun loadQuestionsAndAnswers(): List<Article> {
        return XYRetrofitService.loadQuestionsAndAnswers().data.datas
    }


    suspend fun loadSquareArticles(): List<Article> {
        return XYRetrofitService.loadSquareArticles().data.datas
    }
}