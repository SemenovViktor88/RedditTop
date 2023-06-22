package com.semenov.reddit.data.model.net

import com.semenov.reddit.data.model.db.EntityReddit
import com.semenov.reddit.data.model.domain.Reddit
import com.squareup.moshi.Json

data class RootList(
    @field:Json(name = "data") val data: ApiRedditPage?,
)

data class ApiRedditPage(
    @field:Json(name = "children") val children: List<ApiRedditChildren>?,
    @field:Json(name = "after") val after: String? = null,
)

data class ApiRedditChildren(
    @field:Json(name = "data") val data: ApiReddit?,
)

data class ApiReddit(
    @field:Json(name = "id") val id: String?,
    @field:Json(name = "subreddit") val subreddit: String?,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "thumbnail") val thumbnail: String?,
    @field:Json(name = "author") val author: String?,
    @field:Json(name = "url") val url: String?,
    @field:Json(name = "num_comments") val num_comments: Int?,
    @field:Json(name = "created") val created: Long?,
    @field:Json(name = "ups") val ups: Int?,
    @field:Json(name = "saved") var saved: Boolean = false,
)

fun ApiRedditPage?.toDomainModel(list: List<EntityReddit>): List<Reddit>  {
    return this?.children?.map {
        var position = 0
        while (position < list.size) {
            it.data?.saved = it.data?.id == list[position].id
            if (it.data?.saved!!) {
                position = list.size
                Reddit(
                    id = it.data.id ?: "",
                    subreddit = it.data.subreddit ?: "",
                    title = it.data.title ?: "",
                    thumbnail = it.data.thumbnail ?: "",
                    author = it.data.author ?: "",
                    url = it.data.url ?: "",
                    num_comments = it.data.num_comments ?: 0,
                    created = it.data.created ?: 0,
                    ups = it.data.ups ?: 0,
                    saved = it.data.saved,
                )
            }
            else {
                position++
                Reddit(
                    id = it.data.id ?: "",
                    subreddit = it.data.subreddit ?: "",
                    title = it.data.title ?: "",
                    thumbnail = it.data.thumbnail ?: "",
                    author = it.data.author ?: "",
                    url = it.data.url ?: "",
                    num_comments = it.data.num_comments ?: 0,
                    created = it.data.created ?: 0,
                    ups = it.data.ups ?: 0,
                    saved = it.data.saved,
                )
            }
        }
    Reddit(
        id = it.data?.id ?: "",
        subreddit = it.data?.subreddit ?: "",
        title = it.data?.title ?: "",
        thumbnail = it.data?.thumbnail ?: "",
        author = it.data?.author ?: "",
        url = it.data?.url ?: "",
        num_comments = it.data?.num_comments ?: 0,
        created = it.data?.created ?: 0,
        ups = it.data?.ups ?: 0,
        saved = it.data?.saved!!,
    )
    }.orEmpty()
}

fun ApiReddit.toDomainModel() = Reddit(
    id = id ?: "",
    subreddit = subreddit ?: "",
    title = title ?: "",
    thumbnail = thumbnail ?: "",
    author = author ?: "",
    url = url ?: "",
    num_comments = num_comments ?: 0,
    created = created ?: 0,
    ups = ups ?: 0,
    saved = false,
)