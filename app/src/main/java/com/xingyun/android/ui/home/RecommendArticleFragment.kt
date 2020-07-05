package com.xingyun.android.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentRecommendArticleBinding
import com.xingyun.android.utils.AutoClearedValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RecommendArticleFragment : BaseVMFragment<FragmentRecommendArticleBinding, RecommendArticleViewModel>() {
    override val viewModel: RecommendArticleViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_recommend_article

    private var articleJob: Job? = null

    private var adapter: RecommendArticleAdapter by AutoClearedValue()

    override fun initView() {
        initAdapter()
        lifecycleScope.launch {
            @OptIn(ExperimentalPagingApi::class)
            adapter.dataRefreshFlow.collect {
                binding.rvArticles.scrollToPosition(0)
            }
        }
    }

    override fun initData() {
        loadArticles()
    }

    private fun initAdapter() {
        adapter = RecommendArticleAdapter()
        binding.rvArticles.adapter = adapter.withLoadStateFooter(footer = ArticleLoadStateAdapter { adapter.retry() })
    }

    private fun loadArticles() {
        articleJob?.cancel()
        articleJob = lifecycleScope.launch {
            viewModel.fetchArticle().collectLatest {
                adapter.submitData(it)
            }
        }
    }

}