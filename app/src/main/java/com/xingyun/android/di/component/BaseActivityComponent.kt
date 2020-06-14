package com.xingyun.android.di.component

import com.xingyun.android.base.BaseActivity
import dagger.Subcomponent
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Subcomponent(modules = [AndroidInjectionModule::class])
interface BaseActivityComponent : AndroidInjector<BaseActivity> {

    @Subcomponent.Factory
    interface Factory : AndroidInjector.Factory<BaseActivity>

}