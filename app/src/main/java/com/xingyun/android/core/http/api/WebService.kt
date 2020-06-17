package com.xingyun.android.core.http.api

import com.xingyun.android.core.model.ArticleList
import com.xingyun.android.core.model.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {

    //region article
    @GET("wenda/list/1/json")
    suspend fun loadQuestionsAndAnswers(): ApiResponse<ArticleList>

    @GET("user_article/list/0/json")
    suspend fun loadSquareArticles(): ApiResponse<ArticleList>
    //end region


    //region login
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") password: String): ApiResponse<User>

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