package com.example.reddit.model

import com.example.reddit.RedditChildren

data class RedditData(
    val children: List<RedditChildren>,
    val after: String,
    val before: String,
)