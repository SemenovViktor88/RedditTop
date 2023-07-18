package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RedditRepository {
    val listRedditInDB: StateFlow<List<Reddit>>
    suspend fun getAllReddit(): List<Reddit>
    suspend fun saveRedditInDB(reddit: Reddit)
    fun getAllRedditInDB(): Flow<List<Reddit>>
    suspend fun getRedditDB(id: String): Reddit
    suspend fun deleteRedditDB(id: String)
    suspend fun deleteAllDB()
}