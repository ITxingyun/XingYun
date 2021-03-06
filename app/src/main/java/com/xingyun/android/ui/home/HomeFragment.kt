package com.xingyun.android.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.xingyun.android.R
import com.xingyun.android.common.adapter.PagerAdapter
import com.xingyun.android.ui.article.ArticleType
import com.xingyun.android.ui.article.ArticlesFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val tabTitles = listOf(
            R.string.home_tab_recommend,
            R.string.home_tab_square,
            R.string.home_tab_question_and_answer,
            R.string.home_tab_project
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pagers = mutableListOf(
                PagerAdapter.PagerTab({ ArticlesFragment.newInstance(ArticleType.Recommend) }),
                PagerAdapter.PagerTab({ ArticlesFragment.newInstance(ArticleType.Square) }),
                PagerAdapter.PagerTab({ ArticlesFragment.newInstance(ArticleType.Question) }),
                PagerAdapter.PagerTab({ ArticlesFragment.newInstance(ArticleType.LatestProject) })
        )
        viewPager.adapter = PagerAdapter(childFragmentManager, viewLifecycleOwner.lifecycle, pagers)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(tabTitles[position])
        }.attach()
    }

}