package com.semenov.reddit.presentation.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveViewModel : ViewModel() {

    val listRedditLiveData: MutableLiveData<List<Reddit>> = MutableLiveData()
    private val repository = InstanceProvider.getRepository()

    fun getListEntityRedditVM() {
        viewModelScope.launch {
            val result = repository.getAllRedditDB()
            listRedditLiveData.postValue(result)
        }
    }

    suspend fun deleteRedditDataBase(reddit: Reddit) {
//        withContext(Dispatchers.IO)
       viewModelScope.launch { repository.deleteRedditDB(reddit.id) }
    }
}