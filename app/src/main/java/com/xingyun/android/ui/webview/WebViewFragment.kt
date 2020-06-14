package com.xingyun.android.ui.webview

import androidx.fragment.app.viewModels
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.databinding.FragmentWebViewBinding
import com.xingyun.android.viewmodel.webview.WebViewViewModel
import com.xingyun.android.R

class WebViewFragment : AbstractMVVMFragment<FragmentWebViewBinding, WebViewViewModel>() {

    override val viewModel: WebViewViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_web_view

    override fun initView() {

    }

    override fun initData() {

    }
}