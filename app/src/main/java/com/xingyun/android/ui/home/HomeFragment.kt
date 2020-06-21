package com.xingyun.android.ui.home

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.common.base.DivideLineItemDecorator
import com.xingyun.android.core.model.Article
import com.xingyun.android.core.model.Banner
import com.xingyun.android.databinding.FragmentHomeBinding
import com.xingyun.android.ui.adapter.HomeBannerAdapter
import com.xingyun.android.ui.adapter.ArticleListAdapter
import com.xingyun.android.ui.webview.WebViewFragmentDirections
import com.xingyun.android.utils.autoCleared
import com.youth.banner.indicator.RectangleIndicator

class HomeFragment : BaseVMFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    private var adapter: ArticleListAdapter by autoCleared()

    private var banner: com.youth.banner.Banner<Banner, HomeBannerAdapter> by autoCleared()

    private var bannerAdapter: HomeBannerAdapter by autoCleared()

    override val layoutResourceId: Int = R.layout.fragment_home

    override fun initView() {
        initAdapter()
        binding.rvHome.apply {
            adapter = this@HomeFragment.adapter
            addItemDecoration(DivideLineItemDecorator(resources))
        }
    }

    override fun initData() {
        viewModel.getHomeArticles()
    }

    override fun observe() {
        viewModel.run {
            banners.observe(viewLifecycleOwner, Observer {
                bannerAdapter.update(it)
            })

            uiState.observe(viewLifecycleOwner, Observer {
                it.showSuccess?.let { list ->
                    adapter.run {
                        loadMoreModule.isEnableLoadMore = false
                        if (it.isRefresh) setList(list.datas) else addData(list.datas)
                        loadMoreModule.isEnableLoadMore = true
                        loadMoreModule.loadMoreComplete()
                    }
                }

                if (it.loadMoreEnd) adapter.loadMoreModule.loadMoreEnd()

                it.showError?.let { message ->

                }

            })
        }
    }

    private fun initAdapter() {
        adapter = ArticleListAdapter(R.layout.item_home_article)
        adapter.setOnItemClickListener { adapter, _, position ->
            val article = adapter.data[position]
            if (article is Article) {
                navigation(article.title, article.link)
            }
        }
        addHeaderView()
        initLoadMore()
    }

    private fun addHeaderView() {
        context?.let {
            bannerAdapter = HomeBannerAdapter()

            banner = LayoutInflater.from(it).inflate(R.layout.layout_banner, binding.rvHome, false) as com.youth.banner.Banner<Banner, HomeBannerAdapter>
            banner.setAdapter(bannerAdapter)
                    .addBannerLifecycleObserver(this)
                    .setIndicator(RectangleIndicator(context))
                    .setOnBannerListener { data, _ ->

                    }

        }
        adapter.setHeaderView(banner)
    }

    private fun initLoadMore() {
        adapter.loadMoreModule.run {
            setOnLoadMoreListener { viewModel.getHomeArticles() }
            isAutoLoadMore = true
            isEnableLoadMoreIfNotFullPage = false
        }
    }

    private fun navigation(title: String, url: String) {
        val action = WebViewFragmentDirections.actionGlobalWebViewFragment(title, url)
        findNavController().navigate(action)
    }

}