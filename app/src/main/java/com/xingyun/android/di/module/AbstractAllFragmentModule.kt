package com.xingyun.android.di.module

import com.xingyun.android.di.component.BaseFragmentComponent
import com.xingyun.android.ui.article.ArticlesFragment
import com.xingyun.android.ui.blog.BlogFragment
import com.xingyun.android.ui.home.HomeFragment
import com.xingyun.android.ui.login.LoginFragment
import com.xingyun.android.ui.login.RegistryFragment
import com.xingyun.android.ui.project.ProjectFragment
import com.xingyun.android.ui.search.SearchFragment
import com.xingyun.android.ui.system.SystemFragment
import com.xingyun.android.ui.user.UserProfileFragment
import com.xingyun.android.ui.webview.WebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [BaseFragmentComponent::class])
abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector
    abstract fun loginFragmentInjector(): LoginFragment

    @ContributesAndroidInjector
    abstract fun registryFragmentInjector(): RegistryFragment

    @ContributesAndroidInjector
    abstract fun homeFragmentInjector(): HomeFragment

    @ContributesAndroidInjector
    abstract fun blogFragmentInjector(): BlogFragment

    @ContributesAndroidInjector
    abstract fun articlesFragmentInjector(): ArticlesFragment

    @ContributesAndroidInjector
    abstract fun userProfileFragmentInjector(): UserProfileFragment

    @ContributesAndroidInjector
    abstract fun projectFragmentInjector(): ProjectFragment

    @ContributesAndroidInjector
    abstract fun searchFragmentInjector(): SearchFragment

    @ContributesAndroidInjector
    abstract fun systemFragmentInjector(): SystemFragment

    @ContributesAndroidInjector
    abstract fun webViewFragmentInjector(): WebViewFragment
}