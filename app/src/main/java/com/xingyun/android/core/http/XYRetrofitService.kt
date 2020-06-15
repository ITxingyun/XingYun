package com.xingyun.android.core.http

import com.xingyun.android.core.model.Article
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface XYRetrofitService {

    //region article
    @GET("wenda/list/1/json")
    suspend fun loadQuestionsAndAnswers(): ResponseBody<ListResponseBody<List<Article>>>

    @GET("user_article/list/0/json")
    suspend fun loadSquareArticles(): ResponseBody<ListResponseBody<List<Article>>>

    //end region


    //region login
    @POST("user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") password: String)

    @POST("user/register")
    suspend fun registry(
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("repassword") rePassword: String
    )

    @GET("user/logout/json")
    suspend fun logout()
    //end region
}