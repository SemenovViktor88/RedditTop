package com.semenov.reddit.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel : ViewModel() {

    val listRedditLiveData: LiveData<List<Reddit>>
        get() = _listRedditLiveData
    private val _listRedditLiveData: MutableLiveData<List<Reddit>> = MutableLiveData()
    private val repository = InstanceProvider.getRepository()

    fun getListRedditVM() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getListRedditRepository()
            _listRedditLiveData.postValue(result)
        }
    }

    fun saveReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveRedditInDB(reddit)
            _listRedditLiveData.mutate { list ->
                list?.map {
                    when (reddit.id) {
                        it.id -> reddit.copy(saved = true)
                        else -> it
                    }
                }
            }
        }
    }

    fun removeReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRedditDB(reddit.id)
            _listRedditLiveData.mutate { list ->
                list?.map {
                    when (reddit.id) {
                        it.id -> reddit.copy(saved = false)
                        else -> it
                    }
                }
            }
        }
    }
}

inline fun <reified T: Any?> MutableLiveData<T>.mutate(action: (T?) -> T?){
    postValue(action(value))
}