package com.xingyun.android.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xingyun.android.R
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.databinding.FragmentHomeBinding
import com.xingyun.android.di.component.BaseFragmentComponent
import com.xingyun.android.viewmodel.home.HomeViewModel

class HomeFragment : AbstractMVVMFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_home


}