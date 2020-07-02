package com.xingyun.android.ui.home

import androidx.paging.PagingSource
import com.xingyun.android.model.bean.Article
import com.xingyun.android.model.http.api.WebService
import retrofit2.HttpException
import java.io.IOException
import androidx.paging.PagingSource.LoadResult.Page


class ArticlePageSource(
        private val cid: Int?,
        private val webService: WebService
): PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            val data = webService.getRecommendArticles(page).data
            Page(
                    data = data.datas,
                    prevKey = if (page == 0) null else page,
                    nextKey = if (page == data.pageCount) null else data.pageCount
            )

        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


}