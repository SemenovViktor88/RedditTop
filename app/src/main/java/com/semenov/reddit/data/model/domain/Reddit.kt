package com.semenov.reddit.data.model.domain

import android.os.Parcelable
import com.semenov.reddit.data.model.db.EntityReddit
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reddit(
    val id: String,
    val saved: Boolean,
    val subreddit: String,
    val title: String,
    val thumbnail: String,
    val author: String,
    val url: String,
    val num_comments: Int,
    val created: Long,
    val ups: Int,

): Parcelable{
    companion object {
        fun createEmpty(): Reddit {
            return Reddit("",false,"","","","","",0,0L,0)
        }
    }
}

fun Reddit.toDatabaseModel(): EntityReddit {
    return EntityReddit(
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

data class RedditsPage(
    val items: List<Reddit>,
    val token: String,
)
