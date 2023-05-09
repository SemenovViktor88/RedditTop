package com.semenov.reddit.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.data.network.RedditApi
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    val myLifeData: MutableLiveData<List<ApiRedditChildren>> = MutableLiveData()
    var topApi: RedditApi = InstanceProvider.retrofitService

    fun loadTopList()= viewModelScope.launch {
        val result = topApi.getTopList()?.data?.item!!
        myLifeData.postValue(result)
    }
}