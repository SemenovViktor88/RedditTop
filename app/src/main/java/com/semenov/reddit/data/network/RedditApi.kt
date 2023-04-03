package com.semenov.reddit.data.network

import android.telecom.Call
import com.semenov.reddit.data.model.ApiReddit
import com.semenov.reddit.data.model.ApiRedditPage
import com.semenov.reddit.data.model.RootList
import retrofit2.http.GET

interface  RedditApi {
    @GET("/top.json")
    suspend fun getTopList() : RootList
}