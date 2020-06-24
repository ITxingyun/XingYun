package com.xingyun.android.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.annotation.IntDef
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xingyun.android.R
import com.xingyun.android.ui.blog.BlogFragment
import com.xingyun.android.ui.home.HomeFragment
import com.xingyun.android.ui.project.ProjectFragment
import com.xingyun.android.ui.search.SearchFragment
import com.xingyun.android.ui.user.UserProfileFragment
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment : Fragment(R.layout.fragment_tab), BottomNavigationView.OnNavigationItemSelectedListener {
    private val homeFragment: HomeFragment by lazy { HomeFragment() }
    private val systemFragment: BlogFragment by lazy { BlogFragment() }
    private val searchFragment: SearchFragment by lazy { SearchFragment() }
    private val projectFragment: ProjectFragment by lazy { ProjectFragment() }
    private val userProfileFragment: UserProfileFragment by lazy { UserProfileFragment() }

    private val fragments = listOf(homeFragment, systemFragment, searchFragment, projectFragment, userProfileFragment)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewPager.apply {
            isUserInputEnabled = false
            offscreenPageLimit = 2
            adapter = object : FragmentStateAdapter(this@TabFragment) {
                override fun getItemCount(): Int = fragments.size

                override fun createFragment(position: Int): Fragment = fragments[position]
            }
        }
        navView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_home -> {
                switchFragment(TAB_POSITION_HOME)
            }

            R.id.menu_blog -> {
                switchFragment(TAB_POSITION_SYSTEM)
            }

            R.id.menu_search -> {
                switchFragment(TAB_POSITION_SEARCH)
            }

            R.id.menu_project -> {
                switchFragment(TAB_POSITION_PROJECT)
            }

            R.id.menu_user_profile -> {
                switchFragment(TAB_POSITION_USER_PROFILE)
            }

            else -> true
        }
    }

    private fun switchFragment(@TabPosition tabPosition: Int): Boolean {
        viewPager.setCurrentItem(tabPosition, false)
        return true
    }


    companion object {
        const val TAB_POSITION_HOME = 0
        const val TAB_POSITION_SYSTEM = 1
        const val TAB_POSITION_SEARCH = 2
        const val TAB_POSITION_PROJECT = 3
        const val TAB_POSITION_USER_PROFILE = 4


        @IntDef(value = [TAB_POSITION_HOME, TAB_POSITION_SYSTEM, TAB_POSITION_SEARCH, TAB_POSITION_PROJECT, TAB_POSITION_USER_PROFILE])
        @Retention(AnnotationRetention.SOURCE)
        annotation class TabPosition
    }
}