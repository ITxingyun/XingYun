package com.xingyun.android.ui.community

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.xingyun.android.base.AbstractMVVMFragment
import com.xingyun.android.base.DivideLineItemDecorator
import com.xingyun.android.ui.community.adapter.QuestionAdapter
import com.xingyun.android.viewmodel.community.QuestionViewModel
import com.xingyun.android.databinding.FragmentQuestionBinding
import com.xingyun.android.R
import com.xingyun.android.ui.webview.WebViewActivity
import com.xingyun.android.ui.webview.WebViewActivity.Companion.KEY_BUNDLE_WEB_VIEW_TITLE
import com.xingyun.android.ui.webview.WebViewActivity.Companion.KEY_BUNDLE_WEB_VIEW_URL
import com.xingyun.android.utils.AutoClearedValue
import com.xingyun.android.utils.EventObserver
import com.xingyun.android.utils.start

class QuestionFragment : AbstractMVVMFragment<FragmentQuestionBinding, QuestionViewModel>() {

    override val viewModel by viewModels<QuestionViewModel> { viewModelFactory }

    override val layoutResourceId: Int = R.layout.fragment_question

    private var listAdapter: QuestionAdapter by AutoClearedValue()

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
            activity?.start<WebViewActivity>(configuration = {
                putExtra(KEY_BUNDLE_WEB_VIEW_TITLE, it.title)
                putExtra(KEY_BUNDLE_WEB_VIEW_URL, it.link)
            })
        })
    }

    private fun setupListAdapter() {
        viewModel.items.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    }
}