package com.xingyun.android.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.xingyun.android.model.bean.Banner
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.ArticleRepository

class SearchViewModel(val articleRepository: ArticleRepository): ViewModel() {

    val banners: LiveData<List<Banner>> = liveData {
        val data = articleRepository.getBanners()
        if (data is Result.Success) emit(data.data)
    }
}