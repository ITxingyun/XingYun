package com.xingyun.android.common.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.xingyun.android.di.component.BaseFragmentComponent
import com.xingyun.android.utils.TestObserver
import com.xingyun.android.utils.XYViewModelFactory
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: XYViewModelFactory

    private val testObserver = TestObserver(this)

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    open fun inject(component: BaseFragmentComponent) = Unit

}