package com.xingyun.android.ui.splash

import androidx.fragment.app.viewModels
import com.xingyun.android.R
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.databinding.FragmentLoginBinding
import com.xingyun.android.viewmodel.splash.LoginViewModel

class LoginFragment : AbstractMVVMFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_login




}