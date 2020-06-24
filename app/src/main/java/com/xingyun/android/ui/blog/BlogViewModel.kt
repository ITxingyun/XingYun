package com.xingyun.android.ui.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xingyun.android.model.bean.Category
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.ArticleRepository
import kotlinx.coroutines.launch

class BlogViewModel(private val articleRepository: ArticleRepository): ViewModel() {

    private val _blogCategory = MutableLiveData<List<Category>>()
    val blogCategory: LiveData<List<Category>>
        get() = _blogCategory

    fun fetchBlogCategory() {
        viewModelScope.launch {
            val result = articleRepository.getBlogCategory()
            if (result is Result.Success) {
                val blogCategories = result.data
                _blogCategory.value = blogCategories
            }
        }
    }

}