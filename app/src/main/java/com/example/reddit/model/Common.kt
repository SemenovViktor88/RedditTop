package com.example.reddit.model

import com.example.reddit.model.RedditApi
import com.example.reddit.model.RetrofitHelper

object Common {
	private val BASE_URL = "https://www.reddit.com/"
	val retrofitService: RedditApi
		get() = RetrofitHelper
			.getClient(BASE_URL)
			.create(RedditApi::class.java)

}