package com.xingyun.android.ui.project

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.common.base.DivideLineItemDecorator
import com.xingyun.android.core.model.Article
import com.xingyun.android.databinding.FragmentProjectListBinding
import com.xingyun.android.ui.adapter.ArticleListAdapter
import com.xingyun.android.ui.webview.WebViewFragmentDirections
import com.xingyun.android.utils.autoCleared

class ProductListFragment : BaseVMFragment<FragmentProjectListBinding, ProductListViewModel>() {

    override val viewModel: ProductListViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_project_list

    private var adapter: ArticleListAdapter by autoCleared()

    override fun initView() {
        initAdapter()
        binding.rvProject.apply {
            adapter = this@ProductListFragment.adapter
            addItemDecoration(DivideLineItemDecorator(resources))
        }
    }

    override fun initData() {
        val cid = arguments?.getInt(KEY_BUNDLE_CID) ?: 0
        viewModel.getProjectList(cid)
    }

    override fun observe() {
        viewModel.run {
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
        adapter = ArticleListAdapter(R.layout.item_project)
        adapter.setOnItemClickListener { adapter, view, position ->
            val article = adapter.data[position]
            if (article is Article) {
                navigation(article.title, article.link)
            }
        }
    }

    private fun navigation(title: String, url: String) {
        val action = WebViewFragmentDirections.actionGlobalWebViewFragment(title, url)
        findNavController().navigate(action)
    }

    companion object {
        private const val KEY_BUNDLE_CID = "key_bundle_cid"

        fun newInstance(cid: Int): ProductListFragment {
            return ProductListFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_BUNDLE_CID, cid)
                }
            }
        }
    }
}