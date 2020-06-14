package com.xingyun.android.di.module

import com.xingyun.android.di.component.BaseFragmentComponent
import com.xingyun.android.ui.community.CommunityFragment
import com.xingyun.android.ui.community.QuestionFragment
import com.xingyun.android.ui.community.SquareFragment
import com.xingyun.android.ui.home.HomeFragment
import com.xingyun.android.ui.user.UserProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [BaseFragmentComponent::class])
abstract class AbstractAllFragmentModule {

    @ContributesAndroidInjector(modules = [HomeFragmentModule::class])
    abstract fun homeFragmentInjector(): HomeFragment

    @ContributesAndroidInjector(modules = [UserProfileFragmentModule::class])
    abstract fun userProfileFragmentInjector(): UserProfileFragment

    @ContributesAndroidInjector(modules = [SquareFragmentModule::class])
    abstract fun squareFragmentInjector(): SquareFragment

    @ContributesAndroidInjector(modules = [QuestionFragmentModule::class])
    abstract fun questionFragmentInjector(): QuestionFragment

    @ContributesAndroidInjector(modules = [CommunityFragmentModule::class])
    abstract fun communityFragmentInjector(): CommunityFragment
}