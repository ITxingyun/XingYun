package com.xingyun.android.ui.adapter

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.xingyun.android.BR
import com.xingyun.android.core.model.Article

class ArticleListAdapter(
        @LayoutRes private val layoutResId: Int
) : BaseQuickAdapter<Article, BaseDataBindingHolder<ViewDataBinding>>(layoutResId), LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ViewDataBinding>, item: Article) {
        holder.dataBinding?.setVariable(BR.article, item)
    }

}