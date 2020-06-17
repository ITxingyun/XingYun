package com.xingyun.android.core.source.remote

import com.xingyun.android.core.http.api.ApiResponse
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.http.api.WebService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.User
import java.io.IOException

class RemoteDataSource(private val WebService: WebService) {

    suspend fun loadQuestionsAndAnswers(): List<Article> {
        return WebService.loadQuestionsAndAnswers().data.datas
    }


    suspend fun loadSquareArticles(): List<Article> {
        return WebService.loadSquareArticles().data.datas
    }

    suspend fun login(userName: String, password: String): Result<User> {
        return safeApiCall(call = { requestLogin(userName, password) },
                errorMessage = "")
    }

    private suspend fun requestLogin(userName: String, password: String): Result<User> {
        val response = WebService.login(userName, password)
        return executeResponse(response, {

        })
    }

    suspend fun <T : Any> apiCall(call: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> Result<T>, errorMessage: String): Result<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            Result.Error(IOException(errorMessage, e))
        }
    }

    suspend fun <T : Any> executeResponse(response: ApiResponse<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                          errorBlock: (suspend CoroutineScope.() -> Unit)? = null): Result<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                Result.Error(IOException(response.errorMsg))
            } else {
                successBlock?.let { it() }
                Result.Success(response.data)
            }
        }
    }
}