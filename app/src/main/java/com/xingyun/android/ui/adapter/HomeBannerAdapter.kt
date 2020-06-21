package com.xingyun.android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.xingyun.android.core.model.Banner
import com.xingyun.android.databinding.ItemBannerBinding
import com.youth.banner.adapter.BannerAdapter

class HomeBannerAdapter: BannerAdapter<Banner, BannerViewHolder>(mutableListOf()) {

    override fun onCreateHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val binding = ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BannerViewHolder(binding)
    }

    override fun onBindView(holder: BannerViewHolder, data: Banner, position: Int, size: Int) {
        holder.binding.banner = data
    }

    fun update(banners : List<Banner>) {
        mDatas.addAll(banners)
        notifyDataSetChanged()
    }
}

class BannerViewHolder(val binding: ItemBannerBinding): RecyclerView.ViewHolder(binding.root)