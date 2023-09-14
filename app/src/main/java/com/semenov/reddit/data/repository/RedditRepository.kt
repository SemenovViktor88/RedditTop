package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.data.model.domain.RedditsPage
import kotlinx.coroutines.flow.Flow

interface RedditRepository {
    suspend fun getApiReddit(after: String): RedditsPage
    suspend fun saveReddit(reddit: Reddit)
    fun getAllSavedReddit(): Flow<List<Reddit>>
    suspend fun getSavedReddit(id: String): Reddit
    suspend fun deleteSavedReddit(id: String)
    suspend fun deleteAllSavedReddit()
}