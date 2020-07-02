package com.xingyun.android.ui.home

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.model.source.db.AppDatabase
import com.xingyun.android.model.source.db.Page
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OptIn(ExperimentalPagingApi::class)
class RemoteArticleMediator @Inject constructor(
        private val query: String,
        private val database: AppDatabase,
        private val webService: WebService
) : RemoteMediator<Int, Article>() {
    private val remoteKeyDao = database.remoteKeyDao()
    private val articleDao = database.articleDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Article>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKey = database.withTransaction {
                        remoteKeyDao.remoteKeyByQuery(query)
                    }
                    if (remoteKey.nextPage == null) {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }
                    remoteKey.nextPage
                }
            }

            val response = webService.getRecommendArticles(loadKey)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    remoteKeyDao.deleteByQuery(query)
                    articleDao.deleteByQuery(query)
                }

                remoteKeyDao.insertOrReplace(Page(query, response.data.curPage))

                articleDao.insertArticles(response.data.datas)
            }

            MediatorResult.Success(endOfPaginationReached = response.data.datas.size < 10)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}