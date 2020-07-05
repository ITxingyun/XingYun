package com.xingyun.android.common.app

import android.app.Application
import com.tencent.bugly.Bugly
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import com.xingyun.android.di.component.AppComponent
import com.xingyun.android.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class WanAndroidApplication : Application(), HasAndroidInjector {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        instance = this

//        initCrashReport()

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun initCrashReport() {
        val strategy = UserStrategy(applicationContext).apply {
            appVersion = "1.0.1"
            appChannel = APP_CHANNEL
            isBuglyLogUpload = true
            appPackageName = PACK_NAME
        }

        Bugly.init(applicationContext, BUGLY_APP_ID, true, strategy)
    }

    companion object {
        const val PACK_NAME = "com.xingyun.android"
        const val BUGLY_APP_ID = "b2927a9f71"
        const val APP_CHANNEL = "xiaomi"

        private lateinit var instance: WanAndroidApplication

        @Synchronized
        fun getInstance(): WanAndroidApplication =
            instance

    }
}