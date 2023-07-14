package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RedditRepository {
    val listRedditInDBStateFlow: StateFlow<List<Reddit>>
    fun getListRedditRepository(): Flow<List<Reddit>>
    suspend fun saveRedditInDB(reddit: Reddit)
    fun getAllRedditDB(): Flow<List<Reddit>>
    suspend fun getRedditDB(id: String): Reddit
    suspend fun deleteRedditDB(id: String)
    suspend fun deleteAllDB()
}