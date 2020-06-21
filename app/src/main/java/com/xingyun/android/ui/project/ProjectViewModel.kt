package com.xingyun.android.ui.project

import androidx.lifecycle.*
import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.Category
import com.xingyun.android.core.source.ArticleRepository
import kotlinx.coroutines.launch

class ProjectViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _projectCategory = MutableLiveData<List<Category>>()
    val projectCategory: LiveData<List<Category>>
        get() = _projectCategory

    fun loadProjectCategory() {
        viewModelScope.launch {
            val result = articleRepository.getProjectCategory()
            if (result is Result.Success) {
                val projectCategories = result.data
                _projectCategory.value = projectCategories
            }
        }
    }
}