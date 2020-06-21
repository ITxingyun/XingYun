package com.xingyun.android.ui.project

import androidx.lifecycle.viewModelScope
import com.xingyun.android.common.base.BaseViewModel
import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.ArticleList
import com.xingyun.android.core.source.ArticleRepository
import kotlinx.coroutines.launch

class ProductListViewModel(private val articleRepository: ArticleRepository) : BaseViewModel<ArticleList>() {

    val refreshAction: () -> Unit = { getProjectList(cid, true) }

    private var currentPage = 0

    private var cid = 0

    fun getProjectList(cid: Int, isRefresh: Boolean = false) {
        viewModelScope.launch {
            emitUiState(true)
            if (isRefresh) currentPage = 0
            this@ProductListViewModel.cid = cid

            val result = articleRepository.getProjectList(currentPage, cid)
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