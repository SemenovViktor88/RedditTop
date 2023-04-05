package com.semenov.reddit.data.model

import com.squareup.moshi.Json


data class ApiRedditPage(
    @Json(name = "children")
    val children: List<ApiRedditChildren>? = null,
    val after: String? = null,
)