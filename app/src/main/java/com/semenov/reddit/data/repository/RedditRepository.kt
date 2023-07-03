package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.flow.Flow

interface RedditRepository {

    fun getListRedditRepository(): Flow<List<Reddit>>
    suspend fun saveRedditInDB(reddit: Reddit)
    fun getAllRedditDB(): Flow<List<Reddit>>
    suspend fun getRedditDB(id: String): Reddit
    suspend fun deleteRedditDB(id: String)
}