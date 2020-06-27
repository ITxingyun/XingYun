package com.xingyun.android.model.http.api

import com.xingyun.android.model.bean.*
import retrofit2.http.*

interface WebService {

    @GET("/article/top/json")
    suspend fun getTopArticles(): ApiResponse<List<Article>>

    @GET("/article/list/{page}/json")
    suspend fun getRecommendArticles(@Path("page") page: Int): ApiResponse<ResponseList<Article>>

    @GET("/banner/json")
    suspend fun getBanner(): ApiResponse<List<Banner>>

    @GET("/tree/json")
    suspend fun getSystemCategory(): ApiResponse<List<Category>>

    @GET("/article/list/{page}/json")
    suspend fun getSystemTypeArticles(@Path("page") page: Int, @Query("cid") cid: Int): ApiResponse<ResponseList<Article>>

    @GET("/project/tree/json")
    suspend fun getProjectCategory(): ApiResponse<List<Category>>

    @GET("/wxarticle/chapters/json")
    suspend fun getBlogCategory(): ApiResponse<List<Category>>

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getBlogArticles(@Path("id") id: Int, @Path("page") page: Int): ApiResponse<ResponseList<Article>>

    @GET("/project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): ApiResponse<ResponseList<Article>>

    @GET("/article/listproject/{page}/json")
    suspend fun getLatestProject(@Path("page") page: Int): ApiResponse<ResponseList<Article>>

    @GET("/friend/json")
    suspend fun getWebsites(): ApiResponse<List<Hot>>

    @GET("/hotkey/json")
    suspend fun getHot(): ApiResponse<List<Hot>>

    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    suspend fun searchArticles(@Path("page") page: Int, @Field("k") key: String): ApiResponse<ResponseList<Article>>

    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticles(@Path("page") page: Int): ApiResponse<ResponseList<Article>>

    @POST("/lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Int): ApiResponse<ResponseList<Article>>

    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id: Int): ApiResponse<ResponseList<Article>>

    @GET("/user_article/list/{page}/json")
    suspend fun getSquareArticles(@Path("page") page: Int): ApiResponse<ResponseList<Article>>

    @FormUrlEncoded
    @POST("/lg/user_article/add/json")
    suspend fun shareArticle(@Field("title") title: String, @Field("link") url: String): ApiResponse<String>

    @GET("wenda/list/{page}/json")
    suspend fun getQuestions(@Path("page") page: Int): ApiResponse<ResponseList<Article>>

    @GET("coin/rank/{page}/json")
    suspend fun getRankList(@Path("page") page: Int): ApiResponse<ResponseList<Rank>>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username") userName: String, @Field("password") password: String): ApiResponse<User>

    @POST("user/register")
    suspend fun registry(
            @Field("username") userName: String,
            @Field("password") password: String,
            @Field("repassword") rePassword: String
    ): ApiResponse<User>

    suspend fun logout(): ApiResponse<String>

}