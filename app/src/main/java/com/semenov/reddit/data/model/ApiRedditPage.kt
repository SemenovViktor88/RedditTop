package com.semenov.reddit.data.model

import com.squareup.moshi.Json


data class ApiRedditPage(
    @Json(name = "children")
    val item: List<ApiRedditChildren>? = null,
    val after: String? = null,
)