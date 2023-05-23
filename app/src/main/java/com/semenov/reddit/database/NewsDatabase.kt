package com.semenov.reddit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.semenov.reddit.data.model.db.EntityReddit

@Database(entities = [EntityReddit::class], version = 1)
abstract class RedditDatabase() : RoomDatabase() {
    abstract fun newsDao(): Dao
}

private lateinit var INSTANCE: RedditDatabase
fun getDatabase(context: Context) : RedditDatabase {
    synchronized(RedditDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                RedditDatabase::class.java,
                "news").build()
        }
    }
    return INSTANCE
}