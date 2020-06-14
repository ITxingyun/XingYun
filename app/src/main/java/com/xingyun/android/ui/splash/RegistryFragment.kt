package com.xingyun.android.ui.splash

import androidx.fragment.app.viewModels
import com.xingyun.android.R
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.databinding.FragmentRegistryBinding
import com.xingyun.android.viewmodel.splash.RegistryViewModel

class RegistryFragment : AbstractMVVMFragment<FragmentRegistryBinding, RegistryViewModel>() {

    override val viewModel: RegistryViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_registry

}
