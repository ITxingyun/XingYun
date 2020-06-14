package com.xingyun.android.ui.user

import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.databinding.FragmentUserProfileBinding
import com.xingyun.android.R
import com.xingyun.android.viewmodel.user.UserProfileViewModel

class UserProfileFragment : AbstractMVVMFragment<FragmentUserProfileBinding, UserProfileViewModel>() {

    override val viewModel: UserProfileViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_user_profile

    override fun initView() {

    }

    override fun initData() {
        viewModel.login.observe(viewLifecycleOwner) {

        }
    }

}