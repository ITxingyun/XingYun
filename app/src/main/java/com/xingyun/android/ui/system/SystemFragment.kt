package com.xingyun.android.ui.system

import androidx.fragment.app.viewModels
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentSearchBinding

class SystemFragment: BaseVMFragment<FragmentSearchBinding, SystemViewModel> (){

    override val viewModel: SystemViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_system
}