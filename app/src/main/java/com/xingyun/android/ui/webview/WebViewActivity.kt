package com.xingyun.android.ui.webview

import android.os.Bundle
import com.xingyun.android.base.BaseActivity

class WebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            replaceFragment(WebViewFragment())
        }
    }

}