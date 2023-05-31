package com.semenov.reddit.data.model.domain

import com.semenov.reddit.data.model.db.EntityReddit

data class Reddit(
    val subreddit: String,
    val title: String,
    val thumbnail: String,
    val author: String,
    val url: String,
    val num_comments: Int,
    val created: Long,
    val ups: Int,
)

fun Reddit.toDatabaseModel () : EntityReddit{
    return EntityReddit(
        subreddit = subreddit,
        title = title,
        thumbnail = thumbnail,
        author = author,
        url = url,
        num_comments = num_comments,
        created = created,
        ups = ups
    )
}
