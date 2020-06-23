package com.xingyun.android.ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xingyun.android.model.bean.Category

class CategoryPageAdapter(
        fragment: Fragment
) : FragmentStateAdapter(fragment) {
    private val fragments = mutableListOf<Fragment>()

    private val tabTitle = mutableListOf<String>()

    override fun createFragment(position: Int): Fragment = fragments[position]

    override fun getItemCount() = fragments.size

    fun update(category: List<Category>, createFragment: (Category) -> Fragment) {
        tabTitle.clear()
        tabTitle.addAll(category.map { it.name })

        this.fragments.clear()
        this.fragments.addAll(category.map { createFragment(it) })
        notifyDataSetChanged()
    }

    fun getTabTitle(position: Int): String = tabTitle[position]
}