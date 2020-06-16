package com.xingyun.android.di.module

import com.xingyun.android.di.component.BaseActivityComponent
import com.xingyun.android.ui.MainActivity
import com.xingyun.android.ui.splash.SplashActivity
import com.xingyun.android.ui.webview.WebViewActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(subcomponents = [BaseActivityComponent::class])
abstract class AbstractAllActivityModule {

    @ContributesAndroidInjector
    abstract fun splashActivityInjector(): SplashActivity

    @ContributesAndroidInjector
    abstract fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    abstract fun webViewActivityInjector(): WebViewActivity
}