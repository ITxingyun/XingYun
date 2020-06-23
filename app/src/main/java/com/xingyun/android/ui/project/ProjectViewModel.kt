package com.xingyun.android.ui.project

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xingyun.android.model.bean.Category
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.ArticleRepository
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