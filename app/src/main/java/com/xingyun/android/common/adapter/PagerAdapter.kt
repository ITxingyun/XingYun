package com.xingyun.android.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter<F: Fragment>(
        fragmentManager: FragmentManager,
        lifecycle: Lifecycle,
        private val pagers: MutableList<F> = mutableListOf()
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val tabTitle = mutableListOf<String>()

    override fun getItemCount(): Int = pagers.size

    override fun createFragment(position: Int): Fragment = pagers[position]

    fun getTabTitle(position: Int): String = tabTitle[position]

    fun update(updateAction: (MutableList<F>, MutableList<String>) -> Unit) {
        pagers.clear()
        tabTitle.clear()
        updateAction(pagers, tabTitle)
        notifyDataSetChanged()
    }
}