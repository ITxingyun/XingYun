package com.xingyun.android.model.http.api

import com.xingyun.android.model.bean.*
import retrofit2.http.*

interface WebService {

    @GET("/article/top/json")
    suspend fun getTopArticles(): ApiResponse<List<Article>>

    @GET("/article/list/{page}/json")
    suspend fun getRecommendArticles(@Path("page") page: Int): ApiResponse<ArticleList>

    @GET("/banner/json")
    suspend fun getBanner(): ApiResponse<List<Banner>>

    @GET("/tree/json")
    suspend fun getSystemType(): ApiResponse<List<Category>>

    @GET("/article/list/{page}/json")
    suspend fun getSystemTypeDetail(@Path("page") page: Int, @Query("cid") cid: Int): ApiResponse<ArticleList>

    @GET("/project/tree/json")
    suspend fun getProjectCategory(): ApiResponse<List<Category>>

    @GET("/wxarticle/chapters/json")
    suspend fun getBlogCategory(): ApiResponse<List<Category>>

    @GET("/wxarticle/list/{id}/{page}/json")
    suspend fun getBlogArticles(@Path("id") id: Int, @Path("page") page: Int): ApiResponse<ArticleList>

    @GET("/project/list/{page}/json")
    suspend fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): ApiResponse<ArticleList>

    @GET("/article/listproject/{page}/json")
    suspend fun getLatestProject(@Path("page") page: Int): ApiResponse<ArticleList>

    @GET("/friend/json")
    suspend fun getWebsites(): ApiResponse<List<Hot>>

    @GET("/hotkey/json")
    suspend fun getHot(): ApiResponse<List<Hot>>

    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    suspend fun searchHot(@Path("page") page: Int, @Field("k") key: String): ApiResponse<ArticleList>

    @GET("/lg/collect/list/{page}/json")
    suspend fun getCollectArticles(@Path("page") page: Int): ApiResponse<ArticleList>

    @POST("/lg/collect/{id}/json")
    suspend fun collectArticle(@Path("id") id: Int): ApiResponse<ArticleList>

    @POST("/lg/uncollect_originId/{id}/json")
    suspend fun cancelCollectArticle(@Path("id") id: Int): ApiResponse<ArticleList>

    @GET("/user_article/list/{page}/json")
    suspend fun getSquareArticles(@Path("page") page: Int): ApiResponse<ArticleList>

    @FormUrlEncoded
    @POST("/lg/user_article/add/json")
    suspend fun shareArticle(@Field("title") title: String, @Field("link") url: String): ApiResponse<String>

    @GET("wenda/list/{page}/json")
    suspend fun getQuestions(@Path("page") page: Int): ApiResponse<ArticleList>

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