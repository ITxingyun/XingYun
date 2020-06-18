package com.xingyun.android.ui.adapter

import com.xingyun.android.BR
import com.xingyun.android.R
import com.xingyun.android.core.model.Banner

class BannerAdapter(banners : List<Banner>): SimpleAdapter<Banner>(R.layout.item_banner, banners.toMutableList()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(BR.banner, items[position])
    }
}