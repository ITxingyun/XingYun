package com.xingyun.android.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle,
        private val pagers: MutableList<PagerTab> = mutableListOf()
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = pagers.size

    override fun createFragment(position: Int): Fragment = pagers[position].pagerBuilder()

    fun getTabTitle(position: Int): String = pagers[position].tabTitle

    fun update(pagerTabs: List<PagerTab>) {
        pagers.clear()
        pagers.addAll(pagerTabs)
        notifyDataSetChanged()
    }

    class PagerTab(
        val pagerBuilder: () -> Fragment,
        val tabTitle: String = ""
    )
}