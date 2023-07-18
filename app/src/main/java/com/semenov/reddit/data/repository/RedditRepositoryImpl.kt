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

    override val listRedditInDB: StateFlow<List<Reddit>>
        get() = _listRedditInDB
    private val _listRedditInDB=
        getAllRedditInDB().stateIn(GlobalScope, SharingStarted.Eagerly, emptyList())

    override suspend fun getAllReddit() : List<Reddit> {
        val listApiReddit = remote.getListApiReddit()
        val resultList = listApiReddit?.data?.children?.map { apiRedditChildren ->
            val data = apiRedditChildren.data
            val saved = _listRedditInDB.value.find { reddit ->
                reddit.id == data.id
            } != null
            data.toDomainModel(saved = saved)
        }.orEmpty()
        return resultList
    }

    override fun getAllRedditInDB(): Flow<List<Reddit>> = local.redditDao().getAllReddit().map { it.toDomainModel() }

    override suspend fun saveRedditInDB(reddit: Reddit) {
        val result = reddit.toDatabaseModel()
        local.redditDao().insertReddit(result)
    }

    override suspend fun getRedditDB(id: String) = local.redditDao().getReddit(id).toDomainModel()

    override suspend fun deleteRedditDB(id: String) {
        local.redditDao().deleteReddit(id)
    }

    override suspend fun deleteAllDB() {
        local.redditDao().deleteAll()
    }
}