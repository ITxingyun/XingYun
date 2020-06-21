package com.xingyun.android.common.app

import android.app.Application
import com.tencent.bugly.Bugly
import com.tencent.bugly.crashreport.CrashReport.UserStrategy
import com.tencent.matrix.Matrix
import com.tencent.matrix.iocanary.IOCanaryPlugin
import com.tencent.matrix.iocanary.config.IOConfig
import com.xingyun.android.di.component.AppComponent
import com.xingyun.android.di.component.DaggerAppComponent
import com.xingyun.android.matrix.DynamicConfig
import com.xingyun.android.matrix.MatrixPluginListener
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

//        initMatrix()

//        initCrashReport()

    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    private fun initCrashReport() {
        val strategy = UserStrategy(applicationContext).apply {
            appVersion = "1.0.1"
            appChannel =
                APP_CHANNEL
            isBuglyLogUpload = true
            appPackageName =
                PACK_NAME
        }

        Bugly.init(applicationContext,
            BUGLY_APP_ID, true, strategy)
    }

    private fun initMatrix() {
        val builder: Matrix.Builder = Matrix.Builder(this) // build matrix

        builder.patchListener(MatrixPluginListener(this)) // add general pluginListener

        val dynamicConfig = DynamicConfig() // dynamic config

        // init plugin
        // init plugin
        val ioCanaryPlugin = IOCanaryPlugin(
                IOConfig.Builder()
                        .dynamicConfig(dynamicConfig)
                        .build()
        )
        //add to matrix
        //add to matrix
        builder.plugin(ioCanaryPlugin)

        //init matrix
        //init matrix
        Matrix.init(builder.build())

        // start plugin
        // start plugin
        ioCanaryPlugin.start()
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