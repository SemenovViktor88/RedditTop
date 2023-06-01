package com.semenov.reddit.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.semenov.reddit.data.model.db.EntityReddit

@Database(entities = [EntityReddit::class], version = 1)
abstract class RedditDatabase: RoomDatabase() {
    abstract fun redditDao(): Dao
}