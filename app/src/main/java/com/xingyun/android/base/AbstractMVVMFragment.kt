package com.xingyun.android.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.xingyun.android.utils.XYViewModelFactory
import javax.inject.Inject
import com.xingyun.android.BR
import com.xingyun.android.utils.AutoClearedValue

abstract class AbstractMVVMFragment<B : ViewDataBinding, VM : ViewModel> : BaseFragment() {
    protected var binding: B by AutoClearedValue()

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
        initData()
    }

    open fun initView() = Unit

    open fun initData() = Unit

}