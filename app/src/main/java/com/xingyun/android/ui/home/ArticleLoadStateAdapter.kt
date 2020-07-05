package com.xingyun.android.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.xingyun.android.databinding.ItemArticleFooterViewBinding

class ArticleLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<BaseViewHolder<ItemArticleFooterViewBinding>>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BaseViewHolder<ItemArticleFooterViewBinding> {
        val binding = ItemArticleFooterViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        binding.tvRetry.setOnClickListener { retry.invoke() }
        return BaseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ItemArticleFooterViewBinding>, loadState: LoadState) {
        holder.binding.run {
            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.localizedMessage
            }
            progressBar.isVisible = loadState is LoadState.Loading
            tvRetry.isVisible = loadState !is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
        }

    }


}