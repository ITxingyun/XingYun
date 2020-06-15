package com.xingyun.android.core.source.remote

import com.xingyun.android.core.model.Article
import com.xingyun.android.core.http.XYRetrofitService
import retrofit2.http.Field

class RemoteDataSource(
    private val XYRetrofitService: XYRetrofitService
)  {

    suspend fun loadQuestionsAndAnswers(): List<Article> {
        return XYRetrofitService.loadQuestionsAndAnswers().data.datas
    }


    suspend fun loadSquareArticles(): List<Article> {
        return XYRetrofitService.loadSquareArticles().data.datas
    }

    suspend fun login(userName: String, password: String) {
        XYRetrofitService.login(userName, password)
    }
}