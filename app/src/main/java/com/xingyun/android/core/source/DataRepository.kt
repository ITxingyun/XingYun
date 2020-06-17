package com.xingyun.android.core.source

import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.User
import com.xingyun.android.core.source.local.LocalDataSource
import com.xingyun.android.core.source.remote.RemoteDataSource
import javax.inject.Inject

class DataRepository @Inject constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource) {

    suspend fun loadQuestionsAndAnswers() = remoteDataSource.loadQuestionsAndAnswers()


    suspend fun login(userName: String, password: String): Result<User> {
        return remoteDataSource.login(userName, password)
    }
}