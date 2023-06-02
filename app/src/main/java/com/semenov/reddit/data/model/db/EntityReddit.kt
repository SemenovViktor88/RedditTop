package com.semenov.reddit.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semenov.reddit.data.model.domain.Reddit

@Entity(tableName = "SavedReddit")
data class EntityReddit(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "subreddit") val subreddit: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "num_comments") val num_comments: Int,
    @ColumnInfo(name = "created") val created: Long,
    @ColumnInfo(name = "ups") val ups: Int,
)

fun List<EntityReddit>.toDomainModel(): List<Reddit> {
    return map {
        Reddit(
            id = it.id,
            subreddit = it.subreddit,
            title = it.title,
            thumbnail = it.thumbnail,
            author = it.author,
            url = it.url,
            num_comments = it.num_comments,
            created = it.created,
            ups = it.ups
        )
    }
}

fun EntityReddit.toDomainModel(): Reddit {
    return Reddit(
            id = id,
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