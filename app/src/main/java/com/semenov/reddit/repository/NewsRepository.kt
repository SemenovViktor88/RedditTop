package com.semenov.reddit.repository

import androidx.lifecycle.MutableLiveData
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.database.NewsDatabase
import kotlinx.coroutines.*

class NewsRepository (private val database: NewsDatabase) {
    val myLifeData: MutableLiveData<List<ApiRedditChildren>> = MutableLiveData()
    var topApi: RedditApi = InstanceProvider.retrofitService

    suspend fun loadTopList() {
        withContext(Dispatchers.IO) {
            val result = topApi.getTopList()?.data?.item!!
//            database.newsDao().insertNews(result)
            myLifeData.postValue(result)
        }
    }
}