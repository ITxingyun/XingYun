package com.xingyun.android.ui.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.model.source.db.AppDatabase
import com.xingyun.android.model.source.db.RemoteKey
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

@OptIn(ExperimentalPagingApi::class)
class RemoteArticleMediator(
        private val articleType: String,
        private val database: AppDatabase,
        private val webService: WebService
) : RemoteMediator<Int, Article>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Article>): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                remoteKey?.nextPage?.minus(1) ?: RECOMMEND_ARTICLE_STARTING_PAGE_INDEX
            }
            LoadType.PREPEND -> {
                val remoteKey = getRemoteKeyForFirstItem(state)
                        ?: throw InvalidObjectException("Remote key and the prevKey should not be null")
                remoteKey.prevPage ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKey.prevPage
            }
            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                if (remoteKeys?.nextPage == null) {
                    throw InvalidObjectException("Remote key should not be null for $loadType")
                }
                remoteKeys.nextPage
            }
        }

        return try {
            val response = webService.getRecommendArticles(page)

            val articles = response.data.datas
            articles.forEach {
                it.roomId = "id: ${it.id} - articleType: $articleType"
                it.articleType = articleType
            }
            val endOfPaginationReached = articles.isEmpty()

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeyDao().removeByArticleType(articleType)
                    database.articleDao().removeByArticleType(articleType)
                }
                val prevPage = if (page == RECOMMEND_ARTICLE_STARTING_PAGE_INDEX) null else page - 1
                val nextPage = if (endOfPaginationReached) null else page + 1
                val keys = articles.map { RemoteKey(it.roomId, prevPage, nextPage, articleType) }
                database.remoteKeyDao().insertAll(keys)
                database.articleDao().insertArticles(articles)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, Article>): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
                ?.let { article ->
                    database.remoteKeyDao().remoteKeyByArticle(article.roomId)
                }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, Article>): RemoteKey? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
                ?.let { article ->
                    database.remoteKeyDao().remoteKeyByArticle(article.roomId)
                }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Article>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.roomId?.let { roomId ->
                database.remoteKeyDao().remoteKeyByArticle(roomId)
            }
        }
    }

    private companion object {
        const val RECOMMEND_ARTICLE_STARTING_PAGE_INDEX = 0
    }
}