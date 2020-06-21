package com.xingyun.android.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.xingyun.android.BR
import com.xingyun.android.utils.autoCleared

abstract class BaseVMFragment<B : ViewDataBinding, VM : ViewModel> : BaseFragment() {
    protected var binding by autoCleared<B>()

    protected abstract val viewModel: VM

    protected abstract val layoutResourceId: Int

    protected open val handleCallback = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(handleCallback) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<B>(inflater, layoutResourceId, container, false).also {
                binding = it
            }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            setVariable(BR.viewModel, viewModel)
        }

        initView()
        observe()
        initData()
    }



    open fun initView() = Unit

    open fun observe() = Unit

    open fun initData() = Unit

}