package com.semenov.reddit.data.model

data class ApiRedditPage(
    val item: List<ApiRedditChildren>? = null,
    val after: String? = null,
)