package com.xingyun.android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.xingyun.android.databinding.ItemRecommendArticleBinding
import com.xingyun.android.model.bean.Article

class RecommendArticleAdapter() : PagingDataAdapter<Article, BaseViewHolder<ItemRecommendArticleBinding>>(ArticleComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ItemRecommendArticleBinding> {
        val binding = ItemRecommendArticleBinding.inflate(LayoutInflater.from(parent.context))
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemRecommendArticleBinding>, position: Int) {
        holder.binding.article = getItem(position)
    }

}

object ArticleComparator : DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

}