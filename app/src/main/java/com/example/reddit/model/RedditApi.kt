package com.example.reddit.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface  RedditApi {
    @GET("/top.json")
    fun getTop()
            : Call<RedditTop>;
}