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

fun Reddit.toDatabaseModel (reddit: Reddit) : EntityReddit{
    return EntityReddit(
        subreddit = reddit.subreddit,
        title = reddit.title,
        thumbnail = reddit.thumbnail,
        author = reddit.author,
        url = reddit.url,
        num_comments = reddit.num_comments,
        created = reddit.created,
        ups = reddit.ups
    )
}
