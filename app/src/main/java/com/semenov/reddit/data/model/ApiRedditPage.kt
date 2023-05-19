package com.semenov.reddit.data.model

data class ApiRedditPage(
    val children: List<ApiRedditChildren>? = null,
    val after: String? = null,
)