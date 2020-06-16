package com.xingyun.android.core.source.remote

import androidx.lifecycle.LiveData
import com.xingyun.android.core.http.api.Resource
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.http.api.WebService

class RemoteDataSource(private val WebService: WebService) {

    suspend fun loadQuestionsAndAnswers(): LiveData<Resource<List<Article>>> {
        return WebService.loadQuestionsAndAnswers()
    }


    suspend fun loadSquareArticles(): LiveData<Resource<List<Article>>> {
        return WebService.loadSquareArticles()
    }

    suspend fun login(userName: String, password: String) {
        WebService.login(userName, password)
    }
}