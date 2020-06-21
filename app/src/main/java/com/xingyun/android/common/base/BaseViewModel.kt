package com.xingyun.android.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<T> : ViewModel() {

    private val _uiState = MutableLiveData<UiState<T>>()
    val uiState: LiveData<UiState<T>>
        get() = _uiState

    protected fun emitUiState(
            showLoading: Boolean = false,
            showError: String? = null,
            showSuccess: T? = null,
            loadMoreEnd: Boolean = false,
            isRefresh: Boolean = false
    ) {
        val uiModel = UiState(
                showLoading,
                showError,
                showSuccess,
                loadMoreEnd,
                isRefresh
        )
        _uiState.value = uiModel
    }


    open class UiState<T>(
            var showLoading: Boolean = false,
            var showError: String? = null,
            var showSuccess: T? = null,
            var loadMoreEnd: Boolean = false,
            var isRefresh: Boolean = false
    )
}