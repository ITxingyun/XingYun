package com.xingyun.android.ui.rank

import androidx.lifecycle.viewModelScope
import com.xingyun.android.common.base.BaseViewModel
import com.xingyun.android.model.bean.Rank
import com.xingyun.android.model.http.api.ResponseList
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.SearchRepository
import kotlinx.coroutines.launch

class RankViewModel(private val searchRepository: SearchRepository): BaseViewModel<ResponseList<Rank>>() {
    private var currentPage = 1

    fun fetchRanks() {
        viewModelScope.launch {
            emitUiState(true)
            val result = searchRepository.getRankList(currentPage)
            if (result is Result.Success) {
                val articleList = result.data
                if (articleList.offset >= articleList.total) {
                    emitUiState(showLoading = false, loadMoreEnd = true)
                    return@launch
                }
                currentPage++
                emitUiState(showLoading = false, showSuccess = articleList)
            } else if (result is Result.Error) {
                emitUiState(showLoading = false, showError = result.exception.message)
            }
        }
    }
}