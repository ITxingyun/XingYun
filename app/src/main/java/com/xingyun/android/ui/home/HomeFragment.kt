package com.xingyun.android.ui.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.MergeAdapter
import com.xingyun.android.R
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.base.DivideLineItemDecorator
import com.xingyun.android.databinding.FragmentHomeBinding
import com.xingyun.android.ui.adapter.ArticleListAdapter
import com.xingyun.android.ui.adapter.BannerPagerAdapter
import com.xingyun.android.utils.autoCleared
import com.xingyun.android.viewmodel.home.HomeViewModel

class HomeFragment : AbstractMVVMFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private var mergeAdapter: MergeAdapter by autoCleared()
    private var bannerPagerAdapter: BannerPagerAdapter by autoCleared()
    private var articleAdapter: ArticleListAdapter<HomeViewModel> by autoCleared()

    override val layoutResourceId: Int = R.layout.fragment_home

    override fun initView() {
        bannerPagerAdapter = BannerPagerAdapter()
        articleAdapter = ArticleListAdapter(R.layout.item_top_article, viewModel)
        mergeAdapter = MergeAdapter(bannerPagerAdapter, articleAdapter)
        binding.rvHome.apply {
            adapter = mergeAdapter
            addItemDecoration(DivideLineItemDecorator(resources))
        }
    }

    override fun initData() {
        viewModel.loadData()

        viewModel.run {
            banners.observe(viewLifecycleOwner, Observer {
                bannerPagerAdapter.updateData(listOf(it))
            })

            articles.observe(viewLifecycleOwner, Observer {
                articleAdapter.submitList(it)
            })
        }
    }

}