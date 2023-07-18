package com.semenov.reddit.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    val listRedditLiveData: StateFlow<List<Reddit>>
        get() = _listRedditLiveData
    private val _listRedditLiveData: MutableStateFlow<List<Reddit>> = MutableStateFlow(emptyList())
    private val repository = InstanceProvider.getRepository()
    fun getListRedditVM() {
        viewModelScope.launch {
            _listRedditLiveData.value = repository.getAllReddit()
        }
    }

    fun saveReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveRedditInDB(reddit)
            val list = _listRedditLiveData.value.map {
                when (it.id) {
                    reddit.id -> it.copy(saved = true)
                    else -> it
                }
            }
            _listRedditLiveData.value = list
        }
    }

    fun removeReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRedditDB(reddit.id)
            val list = _listRedditLiveData.value.map {
                when (it.id) {
                    reddit.id -> it.copy(saved = false)
                    else -> it
                }
            }
            _listRedditLiveData.value = list
        }
    }
}

inline fun <reified T : Any?> MutableStateFlow<T>.mutate(action: (T?) -> T?) {
    action(value)
}