package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.domain.Reddit

interface RedditRepository {

    suspend fun getListRedditRepository(): List<Reddit>
    suspend fun saveRedditInDB(reddit: Reddit)
    suspend fun getAllRedditDB(): List<Reddit>
    suspend fun getRedditDB(id: String): Reddit
    suspend fun deleteRedditDB(id: String)
}