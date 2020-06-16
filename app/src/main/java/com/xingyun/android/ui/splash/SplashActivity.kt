package com.xingyun.android.ui.splash

import android.os.Bundle
import com.xingyun.android.base.BaseActivity

class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceFragment(LoginFragment(), false)
        }
    }
}