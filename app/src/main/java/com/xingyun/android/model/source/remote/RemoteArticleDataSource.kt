package com.xingyun.android.model.source.remote

import com.xingyun.android.common.ext.apiCall
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.bean.ArticleList
import com.xingyun.android.model.bean.Banner
import com.xingyun.android.model.bean.Category
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.http.api.WebService

class RemoteArticleDataSource(private val WebService: WebService) {

    suspend fun loadBanner(): Result<List<Banner>> {
        return apiCall{ WebService.getBanner() }
    }

    suspend fun getTopArticles(): Result<List<Article>> {
        return apiCall{ WebService.getTopArticles() }
    }

    suspend fun getRecommendArticles(page: Int): Result<ArticleList> {
        return apiCall{ WebService.getRecommendArticles(page) }
    }

    suspend fun getQuestions(page: Int): Result<ArticleList> {
        return apiCall{ WebService.getQuestions(page) }
    }

    suspend fun getSquareArticles(page: Int): Result<ArticleList> {
        return apiCall{ WebService.getSquareArticles(page) }
    }

    suspend fun getLatestProject(page: Int): Result<ArticleList> {
        return apiCall{ WebService.getLatestProject(page) }
    }

    suspend fun getProjectList(page: Int, cid: Int): Result<ArticleList> {
        return apiCall{ WebService.getProjectList(page, cid) }
    }

    suspend fun getBlogArticles(cid: Int, page: Int): Result<ArticleList> {
        return apiCall{ WebService.getBlogArticles(cid, page) }
    }

    suspend fun getBlogCategory(): Result<List<Category>> {
        return apiCall{ WebService.getBlogCategory() }
    }

    suspend fun getProjectCategory(): Result<List<Category>> {
        return apiCall{ WebService.getProjectCategory() }
    }


}