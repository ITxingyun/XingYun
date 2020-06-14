package com.xingyun.android.core.http

import com.xingyun.android.core.http.ListResponseBody
import com.xingyun.android.core.http.ResponseBody
import com.xingyun.android.core.model.Article
import retrofit2.http.GET

interface XYRetrofitService {

    @GET("wenda/list/1/json")
    suspend fun loadQuestionsAndAnswers(): ResponseBody<ListResponseBody<List<Article>>>

    @GET("user_article/list/0/json")
    suspend fun loadSquareArticles(): ResponseBody<ListResponseBody<List<Article>>>
}