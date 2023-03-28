package com.example.reddit.model


data class RedditData(
    val children: List<RedditChildren>,
    val after: String,
    val before: String,
)