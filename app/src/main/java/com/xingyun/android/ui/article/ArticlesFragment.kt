package com.xingyun.android.ui.article

import androidx.annotation.IntDef
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.common.base.DivideLineItemDecorator
import com.xingyun.android.databinding.FragmentArticlesBinding
import com.xingyun.android.model.bean.Article
import com.xingyun.android.ui.adapter.ArticleListAdapter
import com.xingyun.android.utils.autoCleared
import kotlin.properties.Delegates

class ArticlesFragment : BaseVMFragment<FragmentArticlesBinding, ArticlesViewModel>() {

    override val viewModel: ArticlesViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_articles

    private var adapter: ArticleListAdapter by autoCleared()

    private var articleType by Delegates.notNull<Int>()
    private var cid: Int? = null

    override fun initView() {
        cid = arguments?.getInt(KEY_BUNDLE_CID)
        articleType = arguments?.getInt(KEY_BUNDLE_ARTICLE_TYPE) ?: TYPE_RECOMMEND
        initAdapter()
        binding.rvArticles.apply {
            adapter = this@ArticlesFragment.adapter
            addItemDecoration(DivideLineItemDecorator(resources))
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

            it.showError?.let { message ->

            }
        })
    }

    private fun initAdapter() {
        adapter = ArticleListAdapter(articleType)
        adapter.loadMoreModule.run {
            setOnLoadMoreListener { viewModel.fetchArticles(articleType) }
            isAutoLoadMore = true
            isEnableLoadMoreIfNotFullPage = false
        }
        adapter.setOnItemClickListener { adapter, _, position ->
            val article = adapter.data[position]
            if (article is Article) {
                //TODO
            }
        }
    }

    companion object {
        private const val KEY_BUNDLE_CID = "key_bundle_cid"
        private const val KEY_BUNDLE_ARTICLE_TYPE = "key_bundle_article_type"

        fun newInstance(@ArticleType articleType: Int, cid : Int? = null): ArticlesFragment {
            return ArticlesFragment().apply {
                arguments = bundleOf(
                        KEY_BUNDLE_CID to cid,
                        KEY_BUNDLE_ARTICLE_TYPE to articleType
                )
            }
        }

        const val TYPE_RECOMMEND = 0
        const val TYPE_SQUARE = 1
        const val TYPE_QUESTION = 2
        const val TYPE_PROJECT = 3
        const val TYPE_LATEST_PROJECT = 4

        @IntDef(value = [TYPE_RECOMMEND, TYPE_SQUARE, TYPE_QUESTION, TYPE_PROJECT, TYPE_LATEST_PROJECT])
        @Retention(AnnotationRetention.SOURCE)
        annotation class ArticleType
    }
}