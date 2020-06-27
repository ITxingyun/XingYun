package com.xingyun.android.ui.search

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentSearchArticleBinding

class SearchArticleFragment: BaseVMFragment<FragmentSearchArticleBinding, SearchArticleViewModel>() {

    override val viewModel: SearchArticleViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_search_article

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener { findNavController().popBackStack() }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.searchArticles(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean = false
        })
    }

    override fun initData() {
        viewModel.loadSearchHistory()
    }

    override fun observe() {

    }

}