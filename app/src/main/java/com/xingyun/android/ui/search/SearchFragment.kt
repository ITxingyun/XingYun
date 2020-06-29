package com.xingyun.android.ui.search

import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xingyun.android.R
import com.xingyun.android.common.adapter.DivideLineItemDecorator
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentSearchBinding
import com.xingyun.android.databinding.LayoutSearchNavigationBinding
import com.xingyun.android.model.bean.Banner
import com.xingyun.android.ui.adapter.HomeBannerAdapter
import com.xingyun.android.ui.article.ArticleListAdapter
import com.xingyun.android.ui.article.ArticleType
import com.xingyun.android.utils.AutoClearedValue
import com.xingyun.android.utils.EventObserver
import com.youth.banner.indicator.RectangleIndicator

class SearchFragment: BaseVMFragment<FragmentSearchBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_search

    private var banner: com.youth.banner.Banner<Banner, HomeBannerAdapter> by AutoClearedValue()

    private var bannerAdapter: HomeBannerAdapter by AutoClearedValue()

    private var adapter: ArticleListAdapter by AutoClearedValue()

    override fun initView() {
        initAdapter()
        binding.rvSearch.apply {
            adapter = this@SearchFragment.adapter
            val color = resources.getColor(R.color.coolgray_100, resources.newTheme())
            val divideHeight = resources.getDimensionPixelOffset(R.dimen.dp1)
            addItemDecoration(DivideLineItemDecorator(divideHeight, color))
        }
    }

    override fun initData() {
        viewModel.fetchArticles()
    }

    private fun initAdapter() {
        adapter = ArticleListAdapter(ArticleType.Recommend)
        context?.let {
            bannerAdapter = HomeBannerAdapter()
            banner = LayoutInflater.from(it).inflate(R.layout.layout_banner, binding.rvSearch, false) as com.youth.banner.Banner<Banner, HomeBannerAdapter>
            banner.setAdapter(bannerAdapter)
                    .addBannerLifecycleObserver(this)
                    .setIndicator(RectangleIndicator(context))
                    .setOnBannerListener { data, _ ->

                    }
            adapter.addHeaderView(banner)

            val navigationView = LayoutSearchNavigationBinding.inflate(LayoutInflater.from(it), binding.rvSearch, false)
                    .apply { viewModel = this@SearchFragment.viewModel }
                    .root

            adapter.addHeaderView(navigationView)
            adapter.addHeaderView(LayoutInflater.from(it).inflate(R.layout.layout_top_article_header, binding.rvSearch, false))
        }


    }

    override fun observe() {
        viewModel.run {
            banners.observe(viewLifecycleOwner, Observer {
                bannerAdapter.update(it)
            })

            topArticle.observe(viewLifecycleOwner, Observer {
                adapter.setList(it)
            })

            searchArticleDestination.observe(viewLifecycleOwner, EventObserver {
                findNavController().navigate(R.id.action_tab_fragment_to_searchArticleFragment)
            })

            rankDestination.observe(viewLifecycleOwner, EventObserver {
                findNavController().navigate(R.id.action_tab_fragment_to_rankFragment)
            })

            systemDestination.observe(viewLifecycleOwner, EventObserver {
                findNavController().navigate(R.id.action_tab_fragment_to_systemFragment)
            })

            navigationDestination.observe(viewLifecycleOwner, EventObserver {

            })

            topicDestination.observe(viewLifecycleOwner, EventObserver {

            })
        }
    }

}