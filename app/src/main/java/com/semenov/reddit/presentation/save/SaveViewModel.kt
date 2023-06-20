package com.semenov.reddit.presentation.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    val listRedditLiveData: MutableLiveData<List<Reddit>> = MutableLiveData()
    private val repository = InstanceProvider.getRepository()

    fun getListEntityRedditVM() {
        viewModelScope.launch {
            val result = repository.getAllRedditDB()
            listRedditLiveData.postValue(result)
        }
    }

    suspend fun deleteReddit(reddit: Reddit) {
        viewModelScope.launch { repository.deleteRedditDB(reddit.id) }
    }
}