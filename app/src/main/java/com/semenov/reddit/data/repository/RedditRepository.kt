package com.semenov.reddit.data.repository

import androidx.lifecycle.MutableLiveData
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.data.model.domain.toDatabaseModel
import com.semenov.reddit.data.model.net.toDomainModel
import com.semenov.reddit.data.network.RedditApi as RemoteDataSource
import com.semenov.reddit.data.database.RedditDatabase as LocalDataSource
import kotlinx.coroutines.*

class RedditRepository(
    private val local: LocalDataSource,
    private val remote: RemoteDataSource,
) {
    private lateinit var listReddit: List<Reddit>
    private val myLifeData: MutableLiveData<List<Reddit>> = MutableLiveData()

    suspend fun loadTopList(): List<Reddit> {
        withContext(Dispatchers.IO) {
            listReddit = remote.getTopList()?.data.toDomainModel()
            myLifeData.postValue(listReddit)
        }
        return listReddit
    }

    suspend fun isSaved(reddit: Reddit) {
        val result = reddit.toDatabaseModel()
        local.newsDao().insertNews(result)
    }
}