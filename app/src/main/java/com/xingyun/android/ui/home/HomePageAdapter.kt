package com.xingyun.android.ui.home

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xingyun.android.R
import com.xingyun.android.ui.article.ArticlesFragment.Companion.ArticleType
import com.xingyun.android.ui.article.ArticlesFragment
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_QUESTION
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_RECOMMEND
import com.xingyun.android.ui.article.ArticlesFragment.Companion.TYPE_SQUARE

class HomePageAdapter(
        fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment = ArticlesFragment.newInstance(communityTabSpec[position].articleType)

    override fun getItemCount() = communityTabSpec.size

    @StringRes
    fun getTabTitle(position: Int): Int = communityTabSpec[position].title

    companion object {
        private val communityTabSpec = listOf(
                HomeTabSpec(TYPE_RECOMMEND, R.string.home_tab_recommend),
                HomeTabSpec(TYPE_SQUARE, R.string.home_tab_square),
                HomeTabSpec(TYPE_QUESTION, R.string.home_tab_question_and_answer)
        )

        private class HomeTabSpec(
                @ArticleType val articleType: Int,
                @StringRes val title: Int
        )
    }
}