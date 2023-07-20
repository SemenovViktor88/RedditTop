package com.semenov.reddit.data.network

import com.semenov.reddit.data.model.net.RootList
import retrofit2.http.GET

interface  RedditApi {
    @GET("top.json")
    suspend fun getListApiReddit(): RootList?
}