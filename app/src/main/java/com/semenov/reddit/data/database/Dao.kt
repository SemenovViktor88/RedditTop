package com.semenov.reddit.data.database

import androidx.room.*
import androidx.room.Dao
import com.semenov.reddit.data.model.db.EntityReddit
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReddit(entityReddit: EntityReddit)

    @Query("SELECT * FROM SavedReddit")
    fun getAllReddit(): Flow<List<EntityReddit>>

    @Query("SELECT * FROM SavedReddit WHERE id = :id")
    suspend fun getReddit(id: String): EntityReddit

    @Query("SELECT * FROM SavedReddit WHERE id IN (:ids)")
    suspend fun getReddits(ids: List<String>): List<EntityReddit>

    @Query("DELETE FROM SavedReddit WHERE id = :id")
    suspend fun deleteReddit(id: String)

    @Query("DELETE FROM SavedReddit")
    suspend fun deleteAll()
}