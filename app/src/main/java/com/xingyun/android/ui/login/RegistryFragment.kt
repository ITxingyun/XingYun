package com.xingyun.android.ui.login

import androidx.fragment.app.viewModels
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentRegistryBinding

class RegistryFragment : BaseVMFragment<FragmentRegistryBinding, RegistryViewModel>() {

    override val viewModel: RegistryViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_registry

}
