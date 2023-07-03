package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.db.toDomainModel
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.data.model.domain.toDatabaseModel
import com.semenov.reddit.data.model.net.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import com.semenov.reddit.data.network.RedditApi as RemoteDataSource
import com.semenov.reddit.data.database.RedditDatabase as LocalDataSource

class RedditRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
) : RedditRepository {

    override fun getListRedditRepository(): Flow<List<Reddit>> = flow {
        val listApiReddit = remote.getListApiReddit()?.data
        val listRedditDB = local.redditDao().getAllReddit()
        listApiReddit.toDomainModel(listRedditDB)
    }

    override suspend fun saveRedditInDB(reddit: Reddit) {
        val result = reddit.toDatabaseModel()
        local.redditDao().insertReddit(result)
    }

    override fun getAllRedditDB(): Flow<List<Reddit>> = flow {
        local.redditDao().getAllReddit().toDomainModel()
    }

    override suspend fun getRedditDB(id: String) = local.redditDao().getReddit(id).toDomainModel()

    override suspend fun deleteRedditDB(id: String) {
        local.redditDao().deleteReddit(id)
    }
}