package com.xingyun.android.core.source.remote

import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.http.api.WebService
import com.xingyun.android.core.model.User
import com.xingyun.android.utils.apiCall
import javax.inject.Inject

class RemoteUserProfileDataSource @Inject constructor(private val WebService: WebService) {

    suspend fun login(userName: String, password: String): Result<User> {
        return apiCall { WebService.login(userName, password) }
    }

    suspend fun registry(userName: String, password: String, rePassword: String): Result<User> {
        return apiCall { WebService.registry(userName, password, rePassword) }
    }

    suspend fun logout(): Result<String> {
        return apiCall { WebService.logout() }
    }
}