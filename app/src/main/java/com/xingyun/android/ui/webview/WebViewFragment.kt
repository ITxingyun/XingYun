package com.xingyun.android.ui.webview

import android.content.Context
import android.graphics.Bitmap
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentWebViewBinding

class WebViewFragment : BaseVMFragment<FragmentWebViewBinding, WebViewViewModel>() {
    override val viewModel: WebViewViewModel by viewModels { viewModelFactory }

    private val args: WebViewFragmentArgs by navArgs()

    override val layoutResourceId: Int = R.layout.fragment_web_view

    private var url = ""

    override fun initView() {
        url = args.url
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        initWebView()
    }

    override fun initData() {
        binding.webView.loadUrl(url)
    }

    private fun initWebView() {
        binding.webView.apply {
            settings.apply {
                setAppCacheEnabled(true)
                setAppCachePath(context?.getDir("webview", Context.MODE_PRIVATE)?.path)
                loadsImagesAutomatically = true
            }

            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    binding.progressBar.visibility = View.VISIBLE
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    binding.progressBar.visibility = View.GONE
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onProgressChanged(view: WebView?, newProgress: Int) {
                    binding.progressBar.progress = newProgress
                }

                override fun onReceivedTitle(view: WebView?, title: String?) {
                    title?.let { binding.toolbar.title = it }
                }
            }
        }

    }

    private fun onBackPressed() {
        with(binding.webView) {
            if (canGoBack()) goBack() else findNavController().popBackStack()
        }
    }
}