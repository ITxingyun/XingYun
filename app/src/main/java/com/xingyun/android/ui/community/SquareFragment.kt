package com.xingyun.android.ui.community

import androidx.fragment.app.viewModels
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.databinding.FragmentSquareBinding
import com.xingyun.android.R

class SquareFragment : BaseVMFragment<FragmentSquareBinding, SquareViewModel>() {

    override val viewModel: SquareViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_square

    override fun initData() {

    }
}