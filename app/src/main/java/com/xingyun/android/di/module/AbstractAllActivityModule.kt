package com.xingyun.android.di.module

import com.xingyun.android.di.component.BaseActivityComponent
import com.xingyun.android.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [BaseActivityComponent::class])
abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivityInjector(): MainActivity
}