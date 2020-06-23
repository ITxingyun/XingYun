package com.xingyun.android.ui.home

import androidx.lifecycle.*
import com.xingyun.android.common.base.BaseViewModel
import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.ArticleList
import com.xingyun.android.core.source.ArticleRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val articleRepository: ArticleRepository) : BaseViewModel<ArticleList>() {
    private var currentPage = 0

    val refreshAction: () -> Unit = { getHomeArticles(true) }

    fun getHomeArticles(isRefresh: Boolean = false) {
        viewModelScope.launch {
            emitUiState(true)
            if (isRefresh) currentPage = 0

            val result = articleRepository.getRecommendArticles(currentPage)
            if (result is Result.Success) {
                val articleList = result.data
                if (articleList.offset >= articleList.total) {
                    emitUiState(showLoading = false, loadMoreEnd = true)
                    return@launch
                }
                currentPage++
                emitUiState(showLoading = false, showSuccess = articleList, isRefresh = isRefresh)
            } else if (result is Result.Error) {
                emitUiState(showLoading = false, showError = result.exception.message)
            }
        }
    }


}