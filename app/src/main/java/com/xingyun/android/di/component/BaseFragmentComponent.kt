package com.xingyun.android.di.component

import com.xingyun.android.common.base.BaseFragment
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseFragmentComponent : AndroidInjector<BaseFragment> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<BaseFragment>

}