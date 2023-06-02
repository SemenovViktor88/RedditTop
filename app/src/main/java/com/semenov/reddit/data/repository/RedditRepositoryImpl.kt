package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.db.toDomainModel
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.data.model.domain.toDatabaseModel
import com.semenov.reddit.data.model.net.toDomainModel
import com.semenov.reddit.data.network.RedditApi as RemoteDataSource
import com.semenov.reddit.data.database.RedditDatabase as LocalDataSource
import kotlinx.coroutines.*

class RedditRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource
) : RedditRepository {

    private lateinit var listReddit: List<Reddit>

    override suspend fun getListRedditRepository(): List<Reddit> {
        withContext(Dispatchers.IO) {
            listReddit = remote.getListApiReddit()?.data.toDomainModel()
        }
        return listReddit
    }

    override suspend fun saveRedditInDB(reddit: Reddit) {
        val result = reddit.toDatabaseModel()
        local.redditDao().insertReddit(result)
    }

    override suspend fun getAllRedditDB() = local.redditDao().getAllReddit().toDomainModel()

    override suspend fun getRedditDB(id: String) = local.redditDao().getReddit(id).toDomainModel()

    override suspend fun deleteRedditDB(id: String) {
        local.redditDao().deleteReddit(id)
    }
}