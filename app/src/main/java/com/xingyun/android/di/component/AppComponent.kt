package com.xingyun.android.di.component

import android.content.Context
import com.xingyun.android.common.app.WanAndroidApplication
import com.xingyun.android.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    AndroidInjectionModule::class,
    AbstractAllActivityModule::class,
    AbstractAllFragmentModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(application: WanAndroidApplication)
}