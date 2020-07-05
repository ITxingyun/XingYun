package com.xingyun.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.source.MyArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

@ExperimentalCoroutinesApi
class RecommendArticleViewModel(private val myArticleRepository: MyArticleRepository): ViewModel() {

    fun fetchArticle(): Flow<PagingData<Article>> {
        return myArticleRepository.getRecommendArticles()
                .cachedIn(viewModelScope)
    }

}