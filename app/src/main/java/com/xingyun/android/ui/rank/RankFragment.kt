package com.xingyun.android.ui.rank

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xingyun.android.R
import com.xingyun.android.common.adapter.DivideLineItemDecorator
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.common.ext.showMessage
import com.xingyun.android.databinding.FragmentRankBinding
import com.xingyun.android.utils.autoCleared

class RankFragment : BaseVMFragment<FragmentRankBinding, RankViewModel>() {

    override val viewModel: RankViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_rank

    private var adapter: RankAdapter by autoCleared()

    override fun initView() {
        binding.toolbar.apply {
            title = getString(R.string.search_rank)
            setNavigationOnClickListener { findNavController().popBackStack() }
        }

        initAdapter()
        binding.rvRank.apply {
            adapter = this@RankFragment.adapter
            val color = resources.getColor(R.color.coolgray_100, resources.newTheme())
            val divideHeight = resources.getDimensionPixelOffset(R.dimen.dp1)
            addItemDecoration(DivideLineItemDecorator(divideHeight, color))
        }
    }

    private fun initAdapter() {
        adapter = RankAdapter()
        adapter.loadMoreModule.run {
            setOnLoadMoreListener { viewModel.fetchRanks() }
            isAutoLoadMore = true
            isEnableLoadMoreIfNotFullPage = false
        }
    }

    override fun initData() {
        viewModel.fetchRanks()
    }

    override fun observe() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            it.showSuccess?.let { list ->
                adapter.run {
                    loadMoreModule.isEnableLoadMore = false
                    addData(list.datas)
                    loadMoreModule.isEnableLoadMore = true
                    loadMoreModule.loadMoreComplete()
                }
            }

            if (it.loadMoreEnd) adapter.loadMoreModule.loadMoreEnd()

            it.showError?.let { message -> showMessage(message) }
        })
    }
}