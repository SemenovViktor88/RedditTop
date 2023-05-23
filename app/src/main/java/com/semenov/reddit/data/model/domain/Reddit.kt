package com.semenov.reddit.data.model.domain

data class Reddit(
    val subreddit: String,
    val title: String,
    val thumbnail: String,
    val author: String,
    val url: String,
    val num_comments: Int,
)
