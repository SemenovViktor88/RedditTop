package com.semenov.reddit.data.model


data class RedditData(
    val children: List<RedditChildren>? = null,
    val after: String? = null,
    val before: String? = null,
)