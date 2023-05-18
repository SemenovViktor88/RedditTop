package com.semenov.reddit.repository

import androidx.lifecycle.MutableLiveData
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.NewsReddit
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.data.model.ApiRedditPage
import com.semenov.reddit.data.model.toDatabaseModel
import com.semenov.reddit.data.model.toDomainModel
import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.database.NewsDatabase
import com.semenov.reddit.database.NewsEntity
import com.semenov.reddit.database.toDomainModel
import kotlinx.coroutines.*

class NewsRepository (private val database: NewsDatabase) {
    val myLifeData: MutableLiveData<List<NewsReddit>> = MutableLiveData()
    var topApi: RedditApi = InstanceProvider.retrofitService
    private lateinit var listNewsRedditEntity: List<NewsEntity>
    private lateinit var listNewsReddit: List<NewsReddit>

    suspend fun loadTopList() : List<NewsReddit> {
        withContext(Dispatchers.IO) {
            val result = topApi.getTopList()?.data?.item!!
            listNewsRedditEntity = ApiRedditPage().toDatabaseModel(result)
            database.newsDao().insertNews(listNewsRedditEntity)
            val res = database.newsDao().getNews().toDomainModel()

            listNewsReddit = res
            myLifeData.postValue(listNewsReddit)
        }
        return listNewsReddit
    }
}
