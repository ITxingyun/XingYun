package com.xingyun.android.ui.search

import androidx.lifecycle.viewModelScope
import com.xingyun.android.common.base.BaseViewModel
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.http.api.ResponseList
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.ArticleRepository
import kotlinx.coroutines.launch

class SearchArticleViewModel(private val articleRepository: ArticleRepository) : BaseViewModel<ResponseList<Article>>() {
    private var currentPage = 0
    private var key: String = ""

    val refreshAction: () -> Unit = { searchArticles(key, true) }

    fun loadSearchHistory() {

    }

    fun searchArticles(key: String, isRefresh: Boolean = false) {
        viewModelScope.launch {
            emitUiState(true)
            if (isRefresh) currentPage = 0
            this@SearchArticleViewModel.key = key

            val result = articleRepository.searchArticles(currentPage, key)
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