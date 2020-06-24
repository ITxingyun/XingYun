package com.xingyun.android.ui.project

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.tabs.TabLayoutMediator
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentProjectBinding
import com.xingyun.android.ui.adapter.CategoryPageAdapter
import com.xingyun.android.ui.article.ArticleType
import com.xingyun.android.ui.article.ArticlesFragment
import com.xingyun.android.utils.autoCleared

class ProjectFragment : BaseVMFragment<FragmentProjectBinding, ProjectViewModel>() {

    override val viewModel: ProjectViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_project

    private var adapter: CategoryPageAdapter by autoCleared()

    override fun initView() {
        binding.vpProject.adapter = CategoryPageAdapter(this).also { adapter = it }

        TabLayoutMediator(binding.tabLayout, binding.vpProject) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }

    override fun initData() {
        viewModel.loadProjectCategory()
    }

    override fun observe() {
        viewModel.projectCategory.observe(viewLifecycleOwner, Observer { categories ->
            adapter.update(categories) { ArticlesFragment.newInstance(ArticleType.Project, it.id) }
        })

    }
}