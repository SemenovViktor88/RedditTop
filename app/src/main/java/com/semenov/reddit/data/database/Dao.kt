package com.semenov.reddit.data.database

import androidx.room.*
import androidx.room.Dao
import com.semenov.reddit.data.model.db.EntityReddit

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReddit(entityReddit: EntityReddit)

    @Query("SELECT * FROM SavedReddit")
    suspend fun getAllReddit(): List<EntityReddit>

    @Query("SELECT * FROM SavedReddit WHERE id = :id")
    suspend fun getReddit(id: String): EntityReddit

    @Query("DELETE FROM SavedReddit WHERE id = :id")
    suspend fun deleteReddit(id: String)
}