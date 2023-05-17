package com.semenov.reddit.data.model

data class ApiReddit(
    val subreddit: String,
    val title: String,
    val thumbnail: String,
    val author: String,
    val url: String,
    val num_comments: Int,
)