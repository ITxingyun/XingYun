package com.xingyun.android.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.xingyun.android.R
import com.xingyun.android.utils.autoCleared
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    private var adapter by autoCleared<HomePageAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.adapter = HomePageAdapter(this).also { adapter = it }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(adapter.getTabTitle(position))
        }.attach()
    }

}