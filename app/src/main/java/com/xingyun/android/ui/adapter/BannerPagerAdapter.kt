package com.xingyun.android.ui.adapter

import com.xingyun.android.BR
import com.xingyun.android.R
import com.xingyun.android.core.model.Banner

class BannerPagerAdapter: SimpleAdapter<List<Banner>>(R.layout.item_pager_banner) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.setVariable(BR.data, items[position])
    }

}