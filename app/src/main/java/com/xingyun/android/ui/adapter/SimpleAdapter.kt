package com.xingyun.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class SimpleAdapter<D>(
        @LayoutRes private val itemLayoutRes: Int,
        protected val items: MutableList<D> = mutableListOf()
): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), itemLayoutRes, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(data: List<D>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

}

class ViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root)