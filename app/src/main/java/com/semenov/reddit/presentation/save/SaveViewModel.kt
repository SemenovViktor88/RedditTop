package com.semenov.reddit.presentation.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.presentation.news.mutate
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    val listRedditLiveData: LiveData<List<Reddit>>
        get() = _listRedditLiveData
    private val _listRedditLiveData: MutableLiveData<List<Reddit>> = MutableLiveData()
    private val repository = InstanceProvider.getRepository()

    fun getListEntityRedditVM() {
        viewModelScope.launch {
            val result = repository.getAllRedditDB()
            _listRedditLiveData.postValue(result)
        }
    }

    fun deleteReddit(reddit: Reddit) {
        viewModelScope.launch {
            repository.deleteRedditDB(reddit.id)
            _listRedditLiveData.mutate { list ->
                list?.toMutableList()?.apply { remove(reddit) }
            }
        }
    }
}