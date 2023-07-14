package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.db.toDomainModel
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.data.model.domain.toDatabaseModel
import com.semenov.reddit.data.model.net.toDomainModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import com.semenov.reddit.data.network.RedditApi as RemoteDataSource
import com.semenov.reddit.data.database.RedditDatabase as LocalDataSource

class RedditRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
) : RedditRepository {

    override val listRedditInDBStateFlow: StateFlow<List<Reddit>>
        get() = _listRedditInDBStateFlow
    private val _listRedditInDBStateFlow= getAllRedditDB().stateIn(GlobalScope, SharingStarted.Eagerly, emptyList())

    override fun getListRedditRepository() = flow {
        val listApiReddit = remote.getListApiReddit()?.data
        val resultList = listApiReddit?.children?.mapIndexed { index, apiRedditChildren ->
            val data = apiRedditChildren.data
            val saved = _listRedditInDBStateFlow.value.find {
                it.id == data.id
            } != null
            data.toDomainModel(saved)
        }.orEmpty()
        emit(resultList)
    }

    override suspend fun saveRedditInDB(reddit: Reddit) {
        val result = reddit.toDatabaseModel()
        local.redditDao().insertReddit(result)
    }

    override fun getAllRedditDB(): Flow<List<Reddit>> = local.redditDao().getAllReddit().map { it.toDomainModel() }

    override suspend fun getRedditDB(id: String) = local.redditDao().getReddit(id).toDomainModel()

    override suspend fun deleteRedditDB(id: String) {
        local.redditDao().deleteReddit(id)
    }

    override suspend fun deleteAllDB() {
        local.redditDao().deleteAll()
    }
}