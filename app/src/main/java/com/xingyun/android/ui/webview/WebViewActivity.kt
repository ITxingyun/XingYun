package com.xingyun.android.ui.webview

import android.os.Bundle
import com.xingyun.android.base.BaseActivity

class WebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            intent.run {
                val title = getStringExtra(KEY_BUNDLE_WEB_VIEW_TITLE) ?: ""
                val url = getStringExtra(KEY_BUNDLE_WEB_VIEW_URL) ?: ""
                replaceFragment(WebViewFragment.newInstance(title, url))
            }
        }
    }

    companion object {
        const val KEY_BUNDLE_WEB_VIEW_URL = "key_bundle_web_view_url"
        const val KEY_BUNDLE_WEB_VIEW_TITLE = "key_bundle_web_view_title"
    }

}