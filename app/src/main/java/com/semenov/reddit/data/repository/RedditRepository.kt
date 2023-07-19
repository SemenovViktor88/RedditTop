package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface RedditRepository {
    val listEntityRedditInDB: StateFlow<List<Reddit>>
    suspend fun getApiReddit(): List<Reddit>
    suspend fun saveEntityReddit(reddit: Reddit)
    fun getAllEntityReddit(): Flow<List<Reddit>>
    suspend fun getEntityReddit(id: String): Reddit
    suspend fun deleteEntityReddit(id: String)
    suspend fun deleteAllEntityReddit()
}