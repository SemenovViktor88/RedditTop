package com.semenov.reddit.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel : ViewModel() {

    val listRedditLiveData: MutableLiveData<List<Reddit>> = MutableLiveData()
    private val repository = InstanceProvider.getRepository()

    fun getListRedditVM() {
        viewModelScope.launch {
            val result = repository.getListRedditRepository()
            listRedditLiveData.postValue(result)
        }
    }

    suspend fun saveReddit(reddit: Reddit) {
        withContext(Dispatchers.IO) { repository.saveRedditInDB(reddit) }
    }

    suspend fun removeReddit(reddit: Reddit) {
        withContext(Dispatchers.IO) { repository.deleteRedditDB(reddit.id) }
    }
}