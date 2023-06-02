package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.db.EntityReddit
import com.semenov.reddit.data.model.domain.Reddit

interface RedditRepository {

    suspend fun getListRedditRepository(): List<Reddit>
    suspend fun saveRedditInDB(reddit: Reddit)
    suspend fun getAllRedditDB(): List<EntityReddit>
    suspend fun getRedditDB(id: String): EntityReddit
    suspend fun deleteRedditDB(id: String)
}