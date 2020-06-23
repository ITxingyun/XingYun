package com.xingyun.android.ui.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.xingyun.android.BR
import com.xingyun.android.R
import com.xingyun.android.model.bean.Article
import com.xingyun.android.ui.article.ArticlesFragment.Companion.ArticleType
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_LATEST_PROJECT
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_PROJECT
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_QUESTION
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_RECOMMEND
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_SQUARE

class ArticleListAdapter(
        @ArticleType articleType: Int
) : BaseQuickAdapter<Article, BaseDataBindingHolder<ViewDataBinding>>(generateLayoutRes(articleType)), LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: Article) {
        holder.dataBinding?.setVariable(BR.article, item)
    }

    companion object {
        @LayoutRes
        fun generateLayoutRes(@ArticleType articleType: Int): Int {
            return when (articleType) {
                TYPE_RECOMMEND -> R.layout.item_recommend_article
                TYPE_SQUARE -> R.layout.item_recommend_article
                TYPE_QUESTION -> R.layout.item_question
                TYPE_PROJECT -> R.layout.item_project
                TYPE_LATEST_PROJECT -> R.layout.item_project
                else -> R.layout.item_recommend_article
            }
        }
    }
}