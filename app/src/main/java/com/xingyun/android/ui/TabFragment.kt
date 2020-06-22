package com.xingyun.android.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.xingyun.android.R
import com.xingyun.android.ui.adapter.TabPagerAdapter
import com.xingyun.android.ui.adapter.TabPagerAdapter.Companion.TAB_POSITION_HOME
import com.xingyun.android.ui.adapter.TabPagerAdapter.Companion.TAB_POSITION_PROJECT
import com.xingyun.android.ui.adapter.TabPagerAdapter.Companion.TAB_POSITION_USER_PROFILE
import com.xingyun.android.ui.adapter.TabPagerAdapter.Companion.TAB_POSITION_XXX
import com.xingyun.android.utils.autoCleared
import kotlinx.android.synthetic.main.fragment_tab.*
import com.xingyun.android.ui.adapter.TabPagerAdapter.Companion.TabPosition

class TabFragment : Fragment(R.layout.fragment_tab), BottomNavigationView.OnNavigationItemSelectedListener {
    private var adapter: TabPagerAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TabPagerAdapter(this)
        viewPager.apply {
            adapter = this@TabFragment.adapter
            isUserInputEnabled = false
            offscreenPageLimit = 2
        }
        navView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_home -> {
                switchFragment(TAB_POSITION_HOME)
            }

            R.id.menu_xxx -> {
                switchFragment(TAB_POSITION_XXX)
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
}