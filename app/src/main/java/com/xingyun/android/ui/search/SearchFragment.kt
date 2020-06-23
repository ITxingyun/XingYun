package com.xingyun.android.ui.search

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentSearchBinding
import com.xingyun.android.model.bean.Banner
import com.xingyun.android.ui.adapter.HomeBannerAdapter
import com.xingyun.android.utils.autoCleared

class SearchFragment: BaseVMFragment<FragmentSearchBinding, SearchViewModel>() {

    override val viewModel: SearchViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_search

    private var banner: com.youth.banner.Banner<Banner, HomeBannerAdapter> by autoCleared()

    private var bannerAdapter: HomeBannerAdapter by autoCleared()

    override fun initView() {
        initBanner()
    }

    override fun initData() {

    }

    private fun initBanner() {
        context?.let {
            bannerAdapter = HomeBannerAdapter()

//            banner = LayoutInflater.from(it).inflate(R.layout.layout_banner, binding.rvHome, false) as com.youth.banner.Banner<Banner, HomeBannerAdapter>
//            banner.setAdapter(bannerAdapter)
//                    .addBannerLifecycleObserver(this)
//                    .setIndicator(RectangleIndicator(context))
//                    .setOnBannerListener { data, _ ->
//
//                    }

        }
    }

    override fun observe() {
        viewModel.run {
            banners.observe(viewLifecycleOwner, Observer {
                bannerAdapter.update(it)
            })
        }
    }

}