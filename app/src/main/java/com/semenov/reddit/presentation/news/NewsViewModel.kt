package com.semenov.reddit.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    val listRedditLiveData: StateFlow<List<Reddit>>
        get() = _listRedditLiveData
    private val _listRedditLiveData: MutableStateFlow<List<Reddit>> = MutableStateFlow(emptyList())
    private val repository = InstanceProvider.getRepository()
    fun getListRedditVM() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.listRedditForNewsViewModel.collect {
                _listRedditLiveData.value = it
            }
        }
    }


    fun saveReddit(reddit: Reddit) {
        val list = _listRedditLiveData.value
        list.onEach {
            when (it.id) {
                reddit.id -> it.copy(saved = true)
                else -> it
            }
        }
        _listRedditLiveData.value = list
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveRedditInDB(reddit)
        }
    }

    fun removeReddit(reddit: Reddit) {
        _listRedditLiveData.value.map {
            when (it.id) {
                reddit.id -> it.copy(saved = false)
                else -> it
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRedditDB(reddit.id)

        }
    }
}

inline fun <reified T : Any?> MutableStateFlow<T>.mutate(action: (T?) -> T?) {
    action(value)
}