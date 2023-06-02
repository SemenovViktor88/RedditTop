package com.semenov.reddit.data.repository

import androidx.lifecycle.MutableLiveData
import com.semenov.reddit.data.model.db.EntityReddit
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
    private val myLifeData: MutableLiveData<List<Reddit>> = MutableLiveData()

    override suspend fun getListRedditRepository(): List<Reddit> {
        withContext(Dispatchers.IO) {
            listReddit = remote.getListApiReddit()?.data.toDomainModel()
            myLifeData.postValue(listReddit)
        }
        return listReddit
    }

    override suspend fun saveRedditInDB(reddit: Reddit) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllRedditDB(): List<EntityReddit> {
        TODO("Not yet implemented")
    }

    override suspend fun getRedditDB(id: String): EntityReddit {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRedditDB(id: String) {
        TODO("Not yet implemented")
    }

    suspend fun isSaved(reddit: Reddit) {
        val result = reddit.toDatabaseModel()
        local.redditDao().insertReddit(result)
    }
}