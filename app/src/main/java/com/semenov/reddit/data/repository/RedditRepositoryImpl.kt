package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.db.toDomainModel
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.data.model.domain.toDatabaseModel
import com.semenov.reddit.data.model.net.toDomainModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import com.semenov.reddit.data.database.RedditDatabase as LocalDataSource
import com.semenov.reddit.data.network.RedditApi as RemoteDataSource

class RedditRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
) : RedditRepository {

    override val listEntityRedditInDB: StateFlow<List<Reddit>>
        get() = _listEntityRedditInDB
    private val _listEntityRedditInDB= getAllEntityReddit().stateIn(GlobalScope, SharingStarted.Eagerly, emptyList())

    override suspend fun getApiReddit(): List<Reddit> = remote.getListApiReddit()?.data?.children?.toDomainModel() ?: emptyList()

    override fun getAllEntityReddit(): Flow<List<Reddit>> = local.redditDao().getAllReddit().map { it.toDomainModel() }

    override suspend fun saveEntityReddit(reddit: Reddit) {
        val result = reddit.toDatabaseModel()
        local.redditDao().insertReddit(result)
    }

    override suspend fun getEntityReddit(id: String) = local.redditDao().getReddit(id).toDomainModel()

    override suspend fun deleteEntityReddit(id: String) {
        local.redditDao().deleteReddit(id)
    }

    override suspend fun deleteAllEntityReddit() {
        local.redditDao().deleteAll()
    }
}