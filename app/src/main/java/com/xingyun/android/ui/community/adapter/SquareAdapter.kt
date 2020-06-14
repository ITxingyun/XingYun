package com.xingyun.android.ui.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xingyun.android.viewmodel.community.SquareViewModel
import com.xingyun.android.core.model.Article
import com.xingyun.android.databinding.ItemSquareBinding

class SquareAdapter(private val viewModel: SquareViewModel) :
    PagedListAdapter<Article, SquareAdapter.SquareViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquareViewHolder {
        return SquareViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SquareViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }


    class SquareViewHolder(private val binding: ItemSquareBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: SquareViewModel, article: Article?) {
            binding.article = article
            binding.viewmodel = viewModel
        }

        companion object {
            fun from(parent: ViewGroup) : SquareViewHolder {
                val binding = ItemSquareBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return SquareViewHolder(binding)
            }
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.author == newItem.author
            }

        }
    }


}
