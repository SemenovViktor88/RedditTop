package com.semenov.reddit.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.semenov.reddit.data.model.db.EntityReddit

@Dao
interface Dao {
    @Query("SELECT * FROM news")
    suspend fun getNews(): List<EntityReddit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: EntityReddit)
}