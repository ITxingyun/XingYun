package com.xingyun.android.ui.adapter

import androidx.annotation.IntDef
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseFragment
import com.xingyun.android.ui.community.CommunityFragment
import com.xingyun.android.ui.home.HomeFragment
import com.xingyun.android.ui.project.ProjectFragment
import com.xingyun.android.ui.user.UserProfileFragment

class TabPagerAdapter(
        fragment: Fragment
) : FragmentStateAdapter(fragment) {


    override fun createFragment(position: Int): Fragment = tabSpec[position].tabBuilder()


    override fun getItemCount() = tabSpec.size

    @StringRes
    fun getTabTitle(position: Int): Int = tabSpec[position].title

    companion object {
        private val tabSpec = listOf(
                TabSpec(::HomeFragment, R.string.title_home),
                TabSpec(::CommunityFragment, R.string.title_community),
                TabSpec(::ProjectFragment, R.string.title_project),
                TabSpec(::UserProfileFragment, R.string.title_user)
        )

        private class TabSpec(
                val tabBuilder: () -> BaseFragment,
                @StringRes val title: Int
        )

        const val TAB_POSITION_HOME = 0
        const val TAB_POSITION_XXX = 1
        const val TAB_POSITION_PROJECT = 2
        const val TAB_POSITION_USER_PROFILE = 3


        @IntDef(value = [TAB_POSITION_HOME, TAB_POSITION_XXX, TAB_POSITION_PROJECT, TAB_POSITION_USER_PROFILE])
        @Retention(AnnotationRetention.SOURCE)
        annotation class TabPosition
    }
}