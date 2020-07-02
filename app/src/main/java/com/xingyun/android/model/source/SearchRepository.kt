package com.xingyun.android.model.source

import com.xingyun.android.model.bean.Banner
import com.xingyun.android.model.bean.Rank
import com.xingyun.android.model.http.api.ResponseList
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.source.local.LocalUserProfileDataSource
import com.xingyun.android.model.source.remote.RemoteSearchDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
        private val remoteDataSource: RemoteSearchDataSource,
        private val localDataSource: LocalUserProfileDataSource
) {

    suspend fun getBanners(): Result<List<Banner>> {
        return remoteDataSource.getBanners()
    }

    suspend fun getRankList(page: Int): Result<ResponseList<Rank>> {
        return remoteDataSource.getRankList(page)
    }

}