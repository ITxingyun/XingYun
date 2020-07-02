package com.xingyun.android.ui.home

import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.xingyun.android.model.bean.Article

class RecommendArticleAdapter(): PagingDataAdapter<Article, >(ArticleDiffUtil()) {


}

class ArticleDiffUtil: DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        TODO("Not yet implemented")
    }

}