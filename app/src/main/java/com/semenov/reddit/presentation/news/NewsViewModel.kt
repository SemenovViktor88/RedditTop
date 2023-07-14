package com.semenov.reddit.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.presentation.main.MainViewModel
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
            repository.getListRedditRepository().collect {
                _listRedditLiveData.value = it
            }
        }
    }

    fun saveReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveRedditInDB(reddit)
            _listRedditLiveData.onEach { list ->
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
            _listRedditLiveData.onEach { list ->
                list.map {
                    when (reddit.id) {
                        it.id -> reddit.copy(saved = false)
                        else -> it
                    }
                }
            }
        }
    }
}

inline fun <reified T : Any?> MutableStateFlow<T>.mutate(action: (T?) -> T?) {
    action(value)
}