package com.xingyun.android.ui.blog

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.xingyun.android.R
import com.xingyun.android.common.adapter.PagerAdapter
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentBlogBinding
import com.xingyun.android.ui.article.ArticleType
import com.xingyun.android.ui.article.ArticlesFragment
import com.xingyun.android.utils.AutoClearedValue

class BlogFragment: BaseVMFragment<FragmentBlogBinding, BlogViewModel>() {
    override val viewModel: BlogViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_blog

    private var adapter: PagerAdapter by AutoClearedValue()

    override fun initView() {
        binding.vpBlog.adapter = PagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle).also { adapter = it }
        TabLayoutMediator(binding.tabLayout, binding.vpBlog) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }

    override fun initData() {
        viewModel.fetchBlogCategory()
    }

    override fun observe() {
        viewModel.blogCategory.observe(viewLifecycleOwner, Observer { categories ->
            adapter.update(categories.map { PagerAdapter.PagerTab({ArticlesFragment.newInstance(ArticleType.Project, it.id)}, it.name) })
        })
    }

}