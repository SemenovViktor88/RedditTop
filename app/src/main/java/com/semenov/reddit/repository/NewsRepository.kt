package com.semenov.reddit.repository

import androidx.lifecycle.MutableLiveData
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.data.model.db.EntityReddit
import com.semenov.reddit.data.model.domain.toDatabaseModel
import com.semenov.reddit.data.model.net.ApiRedditPage
import com.semenov.reddit.data.model.net.toDomainModel
import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.database.RedditDatabase
import kotlinx.coroutines.*

class NewsRepository (private val database: RedditDatabase) {
    private lateinit var listRedditEntity: List<EntityReddit>
    private lateinit var listReddit: List<Reddit>
    val myLifeData: MutableLiveData<List<Reddit>> = MutableLiveData()
    var topApi: RedditApi = InstanceProvider.retrofitService

    suspend fun loadTopList() : List<Reddit> {
        withContext(Dispatchers.IO) {
            val resultApi = topApi.getTopList()?.data?.children!!
            listReddit = ApiRedditPage().toDomainModel(resultApi)
            myLifeData.postValue(listReddit)
        }
        return listReddit
    }

    suspend fun isSaved(reddit: Reddit) {
        val result = reddit.toDatabaseModel(reddit)
        database.newsDao().insertNews(result)
    }
}