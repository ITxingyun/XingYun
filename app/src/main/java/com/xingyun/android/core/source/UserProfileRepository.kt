package com.xingyun.android.core.source

import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.User
import com.xingyun.android.core.source.local.LocalUserProfileDataSource
import com.xingyun.android.core.source.remote.RemoteUserProfileDataSource
import javax.inject.Inject

class UserProfileRepository @Inject constructor(
        private val remoteDataSource: RemoteUserProfileDataSource,
        private val localDataSource: LocalUserProfileDataSource
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