package com.xingyun.android.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentRecommendArticleBinding
import com.xingyun.android.utils.AutoClearedValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecommendArticleFragment: BaseVMFragment<FragmentRecommendArticleBinding, RecommendArticleViewModel>() {

    override val viewModel: RecommendArticleViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_recommend_article

    private val adapter : RecommendArticleAdapter by AutoClearedValue()

    override fun initView() {
        binding.rvArticles.adapter = adapter

        lifecycleScope.launch {
            @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
            viewModel.articles.collectLatest {
                adapter.submitData(it)
            }
        }
    }

}