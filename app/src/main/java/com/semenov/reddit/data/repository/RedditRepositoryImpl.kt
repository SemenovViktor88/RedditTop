package com.semenov.reddit.data.repository

import com.semenov.reddit.data.model.db.toDomainModel
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.data.model.domain.RedditsPage
import com.semenov.reddit.data.model.domain.toDatabaseModel
import com.semenov.reddit.data.model.net.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.semenov.reddit.data.database.RedditDatabase as LocalDataSource
import com.semenov.reddit.data.network.RedditApi as RemoteDataSource

class RedditRepositoryImpl(
    private val remote: RemoteDataSource,
    private val local: LocalDataSource,
) : RedditRepository {

    override suspend fun getApiReddit(after: String): RedditsPage {
        val data = remote.getListApiReddit(after = after.takeUnless { it.isEmpty() }).data
        val ids = data.children.map { it.data.id }
        val savedList = local.redditDao().getReddits(ids)
        val result = when {
            savedList.isEmpty() -> data.children.toDomainModel()
            else -> {
                data.children.map { apiReddit ->
                    val saved = savedList
                        .find { reddit -> reddit.id == apiReddit.data.id } != null
                    apiReddit.data.toDomainModel(saved = saved)
                }
            }
        }
        return RedditsPage(
            items = result,
            data.after.orEmpty()
        )
    }

    override fun getAllSavedReddit(): Flow<List<Reddit>> =
        local.redditDao().getAllReddit().map { it.toDomainModel() }

    override suspend fun saveReddit(reddit: Reddit) {
        local.redditDao().insertReddit(reddit.toDatabaseModel())
    }

    override suspend fun getSavedReddit(id: String) =
        local.redditDao().getReddit(id).toDomainModel()

    override suspend fun deleteSavedReddit(id: String) {
        local.redditDao().deleteReddit(id)
    }

    override suspend fun deleteAllSavedReddit() {
        local.redditDao().deleteAll()
    }
}