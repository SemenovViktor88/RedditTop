package com.semenov.reddit.data.network

import com.semenov.reddit.data.model.RootList
import retrofit2.http.GET

interface  RedditApi {
    @GET("/top.json")
    fun getTopList()
            : RootList
}