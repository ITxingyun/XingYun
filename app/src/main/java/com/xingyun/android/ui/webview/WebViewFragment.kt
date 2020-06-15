package com.xingyun.android.ui.webview

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.databinding.FragmentWebViewBinding
import com.xingyun.android.viewmodel.webview.WebViewViewModel
import com.xingyun.android.R

class WebViewFragment : AbstractMVVMFragment<FragmentWebViewBinding, WebViewViewModel>() {

    override val viewModel: WebViewViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_web_view

    private var url = ""

    override fun initView() {
        arguments?.run {
            url = getString(KEY_BUNDLE_WEB_VIEW_URL) ?: ""
            binding.toolbar.title = getString(KEY_BUNDLE_WEB_VIEW_TITLE)
        }

        binding.webView.apply {
            webViewClient = WebViewClient()
        }
    }

    override fun initData() {
        binding.webView.loadUrl(url)
    }

    companion object {
        private const val KEY_BUNDLE_WEB_VIEW_URL = "key_bundle_web_view_url"
        private const val KEY_BUNDLE_WEB_VIEW_TITLE = "key_bundle_web_view_title"

        fun newInstance(title: String, url :String): WebViewFragment {
            return WebViewFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_BUNDLE_WEB_VIEW_TITLE, title)
                    putString(KEY_BUNDLE_WEB_VIEW_URL, url)
                }
            }
        }
    }
}