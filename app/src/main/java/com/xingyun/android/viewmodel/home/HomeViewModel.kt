package com.xingyun.android.viewmodel.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.model.Banner
import com.xingyun.android.core.source.ArticleRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _banners = MutableLiveData<List<Banner>>()
    val banners: LiveData<List<Banner>>
        get() = _banners

    fun loadData() {
        viewModelScope.launch {
            val banners = articleRepository.getBanners()
            if (banners is Result.Success) _banners.value = banners.data

            val topArticles = articleRepository.getTopArticles()
            val articles = articleRepository.getHomeArticles(1)
            if (topArticles is Result.Success && articles is Result.Success) {
                _articles.value = topArticles.data + articles.data.datas
            }
        }
    }

}