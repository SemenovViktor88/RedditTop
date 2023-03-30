package com.semenov.reddit.data.network

import com.semenov.reddit.data.model.RedditTop
import retrofit2.http.GET

interface  RedditApi {
    @GET("/top.json")
    fun getTopList()
            : RedditTop
}