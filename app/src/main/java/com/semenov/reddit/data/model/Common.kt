package com.semenov.reddit.data.model

import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.data.network.RetrofitHelper

object Common {
	private val BASE_URL = "https://www.reddit.com/"
	val retrofitService: RedditApi
		get() = RetrofitHelper.getClient(BASE_URL)
			.create(RedditApi::class.java)

}