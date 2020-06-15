package com.xingyun.android.ui.community.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xingyun.android.viewmodel.community.QuestionViewModel
import com.xingyun.android.core.model.Article
import com.xingyun.android.databinding.ItemQuestionBinding

class QuestionAdapter(private val viewModel: QuestionViewModel): ListAdapter<Article, QuestionAdapter.ViewHolder>(QuestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.form(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(viewModel, item)
    }

    class ViewHolder private constructor(private val binding: ItemQuestionBinding) :
            RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: QuestionViewModel, article: Article) {
            binding.article = article
            binding.viewmodel = viewModel
        }

        companion object {
            fun form(parent: ViewGroup): ViewHolder {
                val binding = ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class QuestionDiffCallback: DiffUtil.ItemCallback<Article>() {

    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

}