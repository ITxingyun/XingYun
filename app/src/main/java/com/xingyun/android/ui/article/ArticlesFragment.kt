package com.xingyun.android.ui.article

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.xingyun.android.R
import com.xingyun.android.common.adapter.DivideLineItemDecorator
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.common.ext.showMessage
import com.xingyun.android.databinding.FragmentArticlesBinding
import com.xingyun.android.model.bean.Article
import com.xingyun.android.ui.webview.WebViewFragmentDirections
import com.xingyun.android.utils.AutoClearedValue
import com.xingyun.android.view.CustomLoadMoreView

class ArticlesFragment : BaseVMFragment<FragmentArticlesBinding, ArticlesViewModel>() {

    override val viewModel: ArticlesViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_articles

    private var adapter: ArticleListAdapter by AutoClearedValue()

    private lateinit var articleType: ArticleType

    private var cid: Int? = null

    override fun initView() {
        cid = arguments?.getInt(KEY_BUNDLE_CID)
        articleType = arguments?.getSerializable(KEY_BUNDLE_ARTICLE_TYPE) as ArticleType
        initAdapter()
        binding.rvArticles.apply {
            adapter = this@ArticlesFragment.adapter
            val color = resources.getColor(R.color.coolgray_100, resources.newTheme())
            val divideHeight = resources.getDimensionPixelOffset(R.dimen.recycler_view_divide_line_height)
            addItemDecoration(DivideLineItemDecorator(divideHeight, color))
        }
    }

    override fun initData() {
        viewModel.fetchArticles(articleType, cid)
    }

    override fun observe() {
        viewModel.uiState.observe(viewLifecycleOwner, Observer {
            it.showSuccess?.let { list ->
                adapter.run {
                    loadMoreModule.isEnableLoadMore = false
                    if (it.isRefresh) setList(list.datas) else addData(list.datas)
                    loadMoreModule.isEnableLoadMore = true
                    loadMoreModule.loadMoreComplete()
                }
            }

            if (it.loadMoreEnd) adapter.loadMoreModule.loadMoreEnd()

            it.showError?.let { message -> showMessage(message) }
        })
    }

    private fun initAdapter() {
        adapter = ArticleListAdapter(articleType)
        adapter.loadMoreModule.run {
            setOnLoadMoreListener { viewModel.fetchArticles(articleType) }
            isAutoLoadMore = true
            isEnableLoadMoreIfNotFullPage = false
            loadMoreView = CustomLoadMoreView()
        }
        adapter.setOnItemClickListener { adapter, _, position ->
            val article = adapter.data[position]
            if (article is Article) {
                navigation(article)
            }
        }
    }

    private fun navigation(article: Article) {
        findNavController().navigate(WebViewFragmentDirections.actionGlobalWebViewFragment(article.title, article.link))
    }

    companion object {
        private const val KEY_BUNDLE_CID = "key_bundle_cid"
        private const val KEY_BUNDLE_ARTICLE_TYPE = "key_bundle_article_type"

        fun newInstance(articleType: ArticleType, cid: Int? = null): ArticlesFragment {
            return ArticlesFragment().apply {
                arguments = bundleOf(
                        KEY_BUNDLE_CID to cid,
                        KEY_BUNDLE_ARTICLE_TYPE to articleType
                )
            }
        }
    }

}