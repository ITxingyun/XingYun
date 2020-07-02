package com.xingyun.android.ui.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.model.source.local.db.AppDatabase

@OptIn(ExperimentalPagingApi::class)
class RemoteArticleMediator(
        private val query: String,
        private val appDatabase: AppDatabase,
        private val webService: WebService
): RemoteMediator<Int, Article>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Article>): MediatorResult {
        TODO("Not yet implemented")
    }
}