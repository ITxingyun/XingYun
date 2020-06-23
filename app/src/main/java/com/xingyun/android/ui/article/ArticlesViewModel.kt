package com.xingyun.android.ui.article

import androidx.lifecycle.viewModelScope
import com.xingyun.android.common.base.BaseViewModel
import com.xingyun.android.common.exception.DataException
import com.xingyun.android.model.bean.ArticleList
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.ArticleRepository
import com.xingyun.android.ui.article.ArticlesFragment.Companion.ArticleType
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_LATEST_PROJECT
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_PROJECT
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_QUESTION
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_RECOMMEND
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_SQUARE
import kotlinx.coroutines.launch

class ArticlesViewModel(private val articleRepository: ArticleRepository) : BaseViewModel<ArticleList>() {
    private var articleType: Int = 0

    private var currentPage = 0

    private var cid: Int? = null

    val refreshAction: () -> Unit = { fetchArticles(articleType, cid, true) }

    fun fetchArticles(@ArticleType articleType: Int, cid: Int? = null, isRefresh: Boolean = false) {
        viewModelScope.launch {
            emitUiState(true)
            if (isRefresh) currentPage = 0
            this@ArticlesViewModel.cid = cid
            this@ArticlesViewModel.articleType = articleType

            val result = getArticle(articleType, cid)
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

    private suspend fun getArticle(@ArticleType articleType: Int, cid: Int?): Result<ArticleList> {
        return when (articleType) {
            TYPE_RECOMMEND -> articleRepository.getRecommendArticles(currentPage)

            TYPE_SQUARE -> articleRepository.getSquareArticles(currentPage)

            TYPE_QUESTION -> articleRepository.getQuestions(currentPage)

            TYPE_PROJECT -> articleRepository.getProjectList(currentPage, cid ?: 0)

            TYPE_LATEST_PROJECT -> articleRepository.getLatestProject(currentPage)

            else -> Result.Error(DataException("未知错误"))
        }
    }
}