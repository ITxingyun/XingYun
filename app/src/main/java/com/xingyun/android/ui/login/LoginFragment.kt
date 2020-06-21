package com.xingyun.android.ui.login

import androidx.fragment.app.viewModels
import com.google.android.material.textfield.TextInputLayout
import com.xingyun.android.R
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentLoginBinding

class LoginFragment : BaseVMFragment<FragmentLoginBinding, LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_login

    override fun initView() {
        binding.onTouchListener = { textInputLayout: TextInputLayout ->
            textInputLayout.isErrorEnabled = false
            false
        }
    }

}