package com.xingyun.android.model.source

import com.xingyun.android.model.bean.User
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.local.LocalSearchDataSource
import com.xingyun.android.model.source.remote.RemoteUserProfileDataSource
import javax.inject.Inject

class UserProfileRepository @Inject constructor(
        private val remoteDataSource: RemoteUserProfileDataSource,
        private val localDataSource: LocalSearchDataSource
) {

    suspend fun login(userName: String, password: String): Result<User> {
        return remoteDataSource.login(userName, password)
    }

    suspend fun registry(userName: String, password: String, rePassword: String): Result<User> {
        return remoteDataSource.registry(userName, password, rePassword)
    }

    suspend fun logout(): Result<String> {
        return remoteDataSource.logout()
    }
}