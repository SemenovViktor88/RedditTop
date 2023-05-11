package com.semenov.reddit.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseNews(
    @PrimaryKey
    val subreddit: String,
    val title: String,
    val thumbnail: String,
    val author: String,
    val url: String,
    val num_comments: Int,
)