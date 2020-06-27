package com.xingyun.android.model.source.remote

import com.xingyun.android.model.bean.Banner
import com.xingyun.android.model.bean.Rank
import com.xingyun.android.model.http.api.ResponseList
import com.xingyun.android.model.http.api.Result
import com.xingyun.android.model.http.api.WebService
import com.xingyun.android.utils.apiCall

class RemoteSearchDataSource(private val webService: WebService) {

    suspend fun getBanners(): Result<List<Banner>> {
        return apiCall { webService.getBanner() }
    }

    suspend fun getRankList(page: Int): Result<ResponseList<Rank>> {
        return apiCall { webService.getRankList(page) }
    }

}