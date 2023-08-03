package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.flow.Flow

interface RedditRepository {
    suspend fun getApiReddit(): List<Reddit>
    suspend fun saveEntityReddit(reddit: Reddit)
    fun getAllEntityReddit(): Flow<List<Reddit>>
    suspend fun getEntityReddit(id: String): Reddit
    suspend fun deleteEntityReddit(id: String)
    suspend fun deleteAllEntityReddit()
}