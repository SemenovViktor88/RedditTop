package com.semenov.reddit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semenov.reddit.NewsReddit

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "subreddit") val subreddit: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "num_comments") val num_comments: Int,
)

fun List<NewsEntity>.toDomainModel() : List<NewsReddit> {
    return map {
        NewsReddit(
            subreddit = it.subreddit,
            title = it.title,
            thumbnail = it.thumbnail,
            author = it.author,
            url = it.url,
            num_comments = it.num_comments
        )
    }
}