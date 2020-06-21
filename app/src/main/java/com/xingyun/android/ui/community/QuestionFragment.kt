package com.xingyun.android.ui.community

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xingyun.android.common.base.BaseVMFragment
import com.xingyun.android.common.base.DivideLineItemDecorator
import com.xingyun.android.ui.community.adapter.QuestionAdapter
import com.xingyun.android.databinding.FragmentQuestionBinding
import com.xingyun.android.R
import com.xingyun.android.utils.EventObserver
import com.xingyun.android.utils.autoCleared

class QuestionFragment : BaseVMFragment<FragmentQuestionBinding, QuestionViewModel>() {

    override val viewModel by viewModels<QuestionViewModel> { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_question

    private var listAdapter by autoCleared<QuestionAdapter>()

    override fun initView() {
        binding.rvQuestion.apply {
            adapter = QuestionAdapter(viewModel).also { listAdapter = it }
            addItemDecoration(DivideLineItemDecorator(resources))
        }
    }

    override fun initData() {
        navigation()
        setupListAdapter()
        viewModel.loadQuestionsAndAnswers()
    }

    private fun navigation() {
        viewModel.articleDetailsDestination.observe(viewLifecycleOwner, EventObserver {

        })
    }

    private fun setupListAdapter() {
        viewModel.items.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }
}