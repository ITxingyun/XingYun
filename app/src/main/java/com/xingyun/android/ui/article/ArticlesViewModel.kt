package com.xingyun.android.ui.article

import androidx.lifecycle.viewModelScope
import com.xingyun.android.common.base.BaseViewModel
import com.xingyun.android.common.exception.DataException
import com.xingyun.android.core.http.api.Result
import com.xingyun.android.core.model.ArticleList
import com.xingyun.android.core.source.ArticleRepository
import kotlinx.coroutines.launch
import com.xingyun.android.ui.article.ArticlesFragment.Companion.ArticleType
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_QUESTION
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_RECOMMEND
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_SQUARE

class ArticlesViewModel(private val articleRepository: ArticleRepository) : BaseViewModel<ArticleList>() {
    private var articleType: Int = 0

    private var currentPage = 0

    val refreshAction: () -> Unit = { fetchArticles(articleType, true) }

    fun fetchArticles(@ArticleType articleType: Int, isRefresh: Boolean = false) {
        viewModelScope.launch {
            emitUiState(true)
            if (isRefresh) currentPage = 0
            this@ArticlesViewModel.articleType = articleType

            val result = getArticle(articleType)
            if (result is Result.Success) {
                val articleList = result.data
                if (articleList.offset >= articleList.total) {
                    emitUiState(showLoading = false, loadMoreEnd = true)
                    return@launch
                }
                currentPage++
                emitUiState(showLoading = false, showSuccess = articleList, isRefresh = isRefresh)
            } else if (result is Result.Error) {
                emitUiState(showLoading = false, showError = result.exception.message)
            }
        }
    }

    private suspend fun getArticle(@ArticleType articleType: Int): Result<ArticleList> {
        return when (articleType) {
            TYPE_RECOMMEND -> articleRepository.getRecommendArticles(currentPage)

            TYPE_SQUARE -> articleRepository.getSquareArticles(currentPage)

            TYPE_QUESTION -> articleRepository.getQuestions(currentPage)

            else -> Result.Error(DataException("未知错误"))
        }
    }
}