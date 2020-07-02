package com.xingyun.android.ui.article

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.xingyun.android.common.base.BaseViewModel
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.http.api.ResponseList
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.ArticleRepository
import kotlinx.coroutines.launch

class ArticlesViewModel(private val articleRepository: ArticleRepository) : BaseViewModel<ResponseList<Article>>() {
    private lateinit var articleType: ArticleType

    private var currentPage = 0

    private var cid: Int? = null

    val refreshAction: () -> Unit = { fetchArticles(articleType, cid, true) }

    fun fetchArticles(articleType: ArticleType, cid: Int? = null, isRefresh: Boolean = false) {
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

    private suspend fun getArticle(articleType: ArticleType, cid: Int?): Result<ResponseList<Article>> {
        return when (articleType) {
            ArticleType.Recommend -> articleRepository.getRecommendArticles(currentPage)

            ArticleType.Square -> articleRepository.getSquareArticles(currentPage)

            ArticleType.Question -> articleRepository.getQuestions(currentPage)

            ArticleType.LatestProject -> articleRepository.getLatestProject(currentPage)

            ArticleType.Project -> articleRepository.getProjectList(currentPage, cid ?: 0)

            ArticleType.Blog -> articleRepository.getBlogArticles(cid ?: 0, currentPage)
        }
    }
}