package com.xingyun.android.ui.webview

import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentWebViewBinding
import com.xingyun.android.R

class WebViewFragment : BaseVMFragment<FragmentWebViewBinding, WebViewViewModel>() {
    override val viewModel: WebViewViewModel by viewModels { viewModelFactory }

    private val args: WebViewFragmentArgs by navArgs()

    override val layoutResourceId: Int = R.layout.fragment_web_view

    private var url = ""

    override fun initView() {
        url = args.url
        binding.toolbar.apply {
            title = args.title
            setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }

        binding.webView.apply {
            webViewClient = WebViewClient()
        }
    }

    override fun initData() {
        binding.webView.loadUrl(url)
    }
}