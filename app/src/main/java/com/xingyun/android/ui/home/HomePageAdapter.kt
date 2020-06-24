package com.xingyun.android.ui.home

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.xingyun.android.R
import com.xingyun.android.ui.article.ArticleType
import com.xingyun.android.ui.article.ArticlesFragment

class HomePageAdapter(
        fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment =
            ArticlesFragment.newInstance(communityTabSpec[position].articleType)

    override fun getItemCount() = communityTabSpec.size

    @StringRes
    fun getTabTitle(position: Int): Int = communityTabSpec[position].title

    companion object {
        private val communityTabSpec = listOf(
                HomeTabSpec(ArticleType.Recommend, R.string.home_tab_recommend),
                HomeTabSpec(ArticleType.Square, R.string.home_tab_square),
                HomeTabSpec(ArticleType.Question, R.string.home_tab_question_and_answer),
                HomeTabSpec(ArticleType.LatestProject, R.string.home_tab_project)
        )

        private class HomeTabSpec(
                val articleType: ArticleType,
                @StringRes val title: Int
        )
    }
}