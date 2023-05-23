package com.semenov.reddit.data.model.net

import com.semenov.reddit.data.model.domain.Reddit
import com.squareup.moshi.Json

data class RootList(
    @field:Json(name = "data") val data: ApiRedditPage? = null,
)

data class ApiRedditPage(
    @field:Json(name = "children") val children: List<ApiRedditChildren>? = null,
    @field:Json(name = "after") val after: String? = null,
)

data class ApiRedditChildren(
    @field:Json(name = "data") val data: ApiReddit? = null,
)

data class ApiReddit(
    @field:Json(name = "subreddit") val subreddit: String? = null,
    @field:Json(name = "title") val title: String? = null,
    @field:Json(name = "thumbnail") val thumbnail: String? = null,
    @field:Json(name = "author") val author: String? = null,
    @field:Json(name = "url") val url: String? = null,
    @field:Json(name = "num_comments") val num_comments: Int? = null,
)

fun ApiRedditPage.toDomainModel(list: List<ApiRedditChildren>): List<Reddit>  {
    return list.map {
        Reddit(
            subreddit = it.data?.subreddit ?: "",
            title = it.data?.title ?: "",
            thumbnail = it.data?.thumbnail ?: "",
            author = it.data?.author ?: "",
            url = it.data?.url ?: "",
            num_comments = it.data?.num_comments ?: 0,
        )
    }
}

fun ApiReddit.toDomainModel(api: ApiReddit) = Reddit(
            subreddit = subreddit ?: "",
            title = title ?: "",
            thumbnail = thumbnail ?: "",
            author = author ?: "",
            url = url ?: "",
            num_comments = num_comments ?: 0,
        )