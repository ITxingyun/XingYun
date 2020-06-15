package com.xingyun.android.ui.community.adapter

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xingyun.android.R
import com.xingyun.android.base.BaseFragment
import com.xingyun.android.ui.community.QuestionFragment
import com.xingyun.android.ui.community.SquareFragment

class CommunityPageAdapter(
        fragment: Fragment
) : FragmentStateAdapter(fragment) {


    override fun createFragment(position: Int): Fragment = communityTabSpec[position].tabBuilder()


    override fun getItemCount() = communityTabSpec.size

    @StringRes
    fun getTabTitle(position: Int): Int = communityTabSpec[position].title

    companion object {
        private val communityTabSpec = listOf(
                CommunityTabSpec(::SquareFragment, R.string.community_tab_question_and_square),
                CommunityTabSpec(::QuestionFragment, R.string.community_tab_question_and_answer)
        )

        private class CommunityTabSpec(
                val tabBuilder: () -> BaseFragment,
                @StringRes val title: Int
        )
    }
}