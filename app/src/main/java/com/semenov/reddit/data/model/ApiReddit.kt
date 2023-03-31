package com.semenov.reddit.data.model

data class ApiReddit(
    val subreddit: String? = null,
    val title: String? = null,
    val thumbnail: String? = null,
    val author: String? = null,
    val url: String? = null,
    val num_comments: Int? = null,
)