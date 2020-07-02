package com.xingyun.android.ui.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.xingyun.android.model.source.MyArticleRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class RecommendArticleViewModel(myArticleRepository: MyArticleRepository): ViewModel() {

    private val clearListCh = Channel<Unit>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val articles = flowOf(
            clearListCh.consumeAsFlow().map { PagingData.empty() },
            myArticleRepository.getRecommendArticles()
    ).flattenMerge(2)

}