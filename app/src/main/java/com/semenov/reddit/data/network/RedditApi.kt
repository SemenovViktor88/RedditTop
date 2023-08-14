package com.semenov.reddit.data.network

import com.semenov.reddit.data.model.net.RootList
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditApi {
    @GET("top.json")
    suspend fun getListApiReddit(
        @Query("after") after: String?,
        @Query("limit") limit: Int = 10,
    ): RootList
}