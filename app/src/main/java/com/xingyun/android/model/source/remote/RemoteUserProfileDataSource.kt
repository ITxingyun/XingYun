package com.xingyun.android.model.source.remote

import com.xingyun.android.utils.apiCall
import com.xingyun.android.model.bean.User
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.http.api.WebService

class RemoteUserProfileDataSource(private val webService: WebService) {

    suspend fun login(userName: String, password: String): Result<User> {
        return apiCall { webService.login(userName, password) }
    }

    suspend fun registry(userName: String, password: String, rePassword: String): Result<User> {
        return apiCall { webService.registry(userName, password, rePassword) }
    }

    suspend fun logout(): Result<String> {
        return apiCall { webService.logout() }
    }
}