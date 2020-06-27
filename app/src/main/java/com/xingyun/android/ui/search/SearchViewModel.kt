package com.xingyun.android.ui.search

import androidx.lifecycle.*
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.bean.Banner
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.ArticleRepository
import com.xingyun.android.model.source.SearchRepository
import com.xingyun.android.utils.Event
import kotlinx.coroutines.launch

class SearchViewModel(
        private val searchRepository: SearchRepository,
        private val articleRepository: ArticleRepository
) : ViewModel() {

    val banners: LiveData<List<Banner>> = liveData {
        val data = searchRepository.getBanners()
        if (data is Result.Success) emit(data.data)
    }

    private val _topArticle = MutableLiveData<List<Article>>()
    val topArticle: LiveData<List<Article>>
        get() = _topArticle

    fun fetchArticles() {
        viewModelScope.launch {
            val result = articleRepository.getTopArticles()
            if (result is Result.Success) {
                _topArticle.value = result.data
            }
        }
    }

    private val _searchArticleDestination = MutableLiveData<Event<Unit>>()
    val searchArticleDestination: LiveData<Event<Unit>>
        get() = _searchArticleDestination

    fun navigateToSearchArticlePage() {
        _searchArticleDestination.value = Event(Unit)
    }

    private val _rankDestination = MutableLiveData<Event<Unit>>()
    val rankDestination: LiveData<Event<Unit>>
        get() = _rankDestination

    fun navigateToRankPage() {
        _rankDestination.value = Event(Unit)
    }

    private val _systemDestination = MutableLiveData<Event<Unit>>()
    val systemDestination: LiveData<Event<Unit>>
        get() = _systemDestination

    fun navigateToSystemPage() {
        _systemDestination.value = Event(Unit)
    }

    private val _navigationDestination = MutableLiveData<Event<Unit>>()
    val navigationDestination: LiveData<Event<Unit>>
        get() = _navigationDestination

    fun navigateToNavigationPage() {
        _navigationDestination.value = Event(Unit)
    }

    private val _topicDestination = MutableLiveData<Event<Unit>>()
    val topicDestination: LiveData<Event<Unit>>
        get() = _topicDestination

    fun navigateToTopicPage() {
        _topicDestination.value = Event(Unit)
    }

}