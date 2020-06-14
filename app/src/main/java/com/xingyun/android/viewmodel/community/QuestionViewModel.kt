package com.xingyun.android.viewmodel.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.source.DataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionViewModel(private val dataRepository: DataRepository) : ViewModel() {

    private val _items = MutableLiveData<List<Article>>().apply { value = emptyList() }
    val items: LiveData<List<Article>> = _items

    fun loadQuestionsAndAnswers() {
        viewModelScope.launch {
            _items.value = withContext(Dispatchers.IO) {
                dataRepository.loadQuestionsAndAnswers()
            }
        }
    }

}