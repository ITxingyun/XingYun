package com.xingyun.android.ui.rank

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder
import com.xingyun.android.R
import com.xingyun.android.databinding.ItemRankBinding
import com.xingyun.android.model.bean.Rank

class RankAdapter: BaseQuickAdapter<Rank, BaseDataBindingHolder<ItemRankBinding>>(R.layout.item_rank), LoadMoreModule {

    override fun convert(holder: BaseDataBindingHolder<ItemRankBinding>, item: Rank) {
        holder.dataBinding?.rank = item
    }

}