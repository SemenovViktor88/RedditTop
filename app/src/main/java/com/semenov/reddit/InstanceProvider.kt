package com.semenov.reddit

import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.data.network.RetrofitHelper

object InstanceProvider {
	private const val BASE_URL = "https://www.reddit.com/"
	val retrofitService: RedditApi = RetrofitHelper.getClient(BASE_URL)
			.create(RedditApi::class.java)
}