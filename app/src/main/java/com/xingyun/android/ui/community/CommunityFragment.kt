package com.xingyun.android.ui.community

import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.xingyun.android.R
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.viewmodel.community.CommunityViewModel
import com.xingyun.android.ui.community.adapter.CommunityPageAdapter
import com.xingyun.android.databinding.FragmentCommunityBinding
import com.xingyun.android.utils.AutoClearedValue

class CommunityFragment : AbstractMVVMFragment<FragmentCommunityBinding, CommunityViewModel>() {

    override val viewModel by viewModels<CommunityViewModel> { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_community

    private var adapter: CommunityPageAdapter by AutoClearedValue()

    override fun initView() {
        binding.vpCommunity.adapter = CommunityPageAdapter(this@CommunityFragment).also { adapter = it }

        TabLayoutMediator(binding.tabLayout, binding.vpCommunity) { tab, position ->
            tab.text = getString(adapter.getTabTitle(position))
        }.attach()
    }

    override fun initData() {

    }

}