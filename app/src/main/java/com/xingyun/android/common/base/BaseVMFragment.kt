package com.xingyun.android.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.xingyun.android.BR
import com.xingyun.android.utils.AutoClearedValue

abstract class BaseVMFragment<B : ViewDataBinding, VM : ViewModel> : BaseFragment() {
    protected var binding by AutoClearedValue<B>()

    protected abstract val viewModel: VM

    protected abstract val layoutResourceId: Int

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