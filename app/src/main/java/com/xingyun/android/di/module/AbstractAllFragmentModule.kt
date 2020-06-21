package com.xingyun.android.di.module

import com.xingyun.android.di.component.BaseFragmentComponent
import com.xingyun.android.ui.community.CommunityFragment
import com.xingyun.android.ui.community.QuestionFragment
import com.xingyun.android.ui.community.SquareFragment
import com.xingyun.android.ui.home.HomeFragment
import com.xingyun.android.ui.project.ProductListFragment
import com.xingyun.android.ui.project.ProjectFragment
import com.xingyun.android.ui.login.LoginFragment
import com.xingyun.android.ui.login.RegistryFragment
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
    abstract fun userProfileFragmentInjector(): UserProfileFragment

    @ContributesAndroidInjector
    abstract fun squareFragmentInjector(): SquareFragment

    @ContributesAndroidInjector
    abstract fun questionFragmentInjector(): QuestionFragment

    @ContributesAndroidInjector
    abstract fun communityFragmentInjector(): CommunityFragment

    @ContributesAndroidInjector
    abstract fun projectFragmentInjector(): ProjectFragment

    @ContributesAndroidInjector
    abstract fun productListFragmentInjector(): ProductListFragment

    @ContributesAndroidInjector
    abstract fun webViewFragmentInjector(): WebViewFragment
}