package com.xingyun.android.ui.community

import androidx.fragment.app.viewModels
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.viewmodel.community.SquareViewModel
import com.xingyun.android.databinding.FragmentSquareBinding
import com.xingyun.android.R

class SquareFragment : AbstractMVVMFragment<FragmentSquareBinding, SquareViewModel>() {

    override val viewModel: SquareViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_square

    override fun initData() {

    }
}