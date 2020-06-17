package com.xingyun.android.viewmodel.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.source.ArticleRepository
import com.xingyun.android.utils.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _items = MutableLiveData<List<Article>>().apply { value = emptyList() }
    val items: LiveData<List<Article>>
        get() = _items

    private val _articleDetailsDestination = MutableLiveData<Event<Article>>()
    val articleDetailsDestination: LiveData<Event<Article>>
        get() = _articleDetailsDestination

    fun loadQuestionsAndAnswers() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val result = withContext(Dispatchers.IO) { articleRepository.loadQuestionsAndAnswers() }
                if (result is Result.Success) _items.value = result.data.datas
            }
        }
    }

    fun navigateToArticleDetailsPage(article: Article) {
        _articleDetailsDestination.value = Event(article)
    }

}