package com.semenov.reddit.data.model

import com.semenov.reddit.NewsReddit
import com.semenov.reddit.database.NewsEntity

data class RootList(val data: ApiRedditPage?,)

fun ApiRedditPage.toDomainModel(): List<NewsReddit>  {
    return item?.map {
        NewsReddit(
            subreddit = it.data!!.subreddit,
            title = it.data.title,
            thumbnail = it.data.thumbnail,
            author = it.data.author,
            url = it.data.url,
            num_comments = it.data.num_comments
        )
    }!!
}

fun ApiRedditPage.toDatabaseModel(): List<NewsEntity>  {
    return item?.map {
        NewsEntity(
            subreddit = it.data!!.subreddit,
            title = it.data.title,
            thumbnail = it.data.thumbnail,
            author = it.data.author,
            url = it.data.url,
            num_comments = it.data.num_comments
        )
    }!!
}