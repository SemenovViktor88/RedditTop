package com.semenov.reddit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDatabase() : RoomDatabase(){
    abstract fun newsDao(): NewsDao
    companion object{
        fun getDatabase(context: Context) : NewsDatabase{
            return Room.databaseBuilder(context, NewsDatabase::class.java, "News").build()
        }
    }
}