package com.semenov.reddit.presentation.save

import androidx.lifecycle.*
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.presentation.news.mutate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    private val repository = InstanceProvider.getRepository()
    val listRedditLiveData: LiveData<List<Reddit>>
        get() = _listRedditLiveData
    private val _listRedditLiveData: MutableLiveData<List<Reddit>> = MutableLiveData()


    fun getListEntityRedditVM() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllRedditDB().collect {
                _listRedditLiveData.postValue(it)
            }
        }
    }

    fun deleteReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRedditDB(reddit.id)
            _listRedditLiveData.mutate {
                it?.toMutableList()?.apply { remove(reddit) }
            }
        }
    }
}