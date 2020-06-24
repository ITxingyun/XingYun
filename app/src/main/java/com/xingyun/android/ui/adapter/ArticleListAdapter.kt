package com.xingyun.android.ui.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.xingyun.android.BR
import com.xingyun.android.R
import com.xingyun.android.model.bean.Article
import com.xingyun.android.ui.article.ArticleType

class ArticleListAdapter(
        articleType: ArticleType
) : BaseQuickAdapter<Article, BaseDataBindingHolder<ViewDataBinding>>(generateLayoutRes(articleType)), LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: Article) {
        holder.dataBinding?.setVariable(BR.article, item)
    }

    companion object {
        @LayoutRes
        fun generateLayoutRes(articleType: ArticleType): Int {
            return when (articleType) {
                ArticleType.Recommend -> R.layout.item_recommend_article

                ArticleType.Square -> R.layout.item_recommend_article

                ArticleType.Question -> R.layout.item_question

                ArticleType.LatestProject -> R.layout.item_project

                ArticleType.Project -> R.layout.item_project

                ArticleType.Blog -> R.layout.item_recommend_article
            }
        }
    }
}