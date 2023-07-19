package com.semenov.reddit.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    init {
        getListReddit()
    }

    val listReddit: StateFlow<List<Reddit>>
        get() = _listReddit
    private val _listReddit: MutableStateFlow<List<Reddit>> = MutableStateFlow(emptyList())
    private val repository = InstanceProvider.getRepository()

    private fun getListReddit() {
        viewModelScope.launch(Dispatchers.Main) {
            _listReddit.value = repository.getApiReddit()
            val listEntityReddit = repository.listEntityRedditInDB
            _listReddit.combine(listEntityReddit) { listReddit, _listEntityReddit ->
                _listReddit.value = listReddit.map {
                    val saved = _listEntityReddit.find { reddit ->
                        reddit.id == it.id
                    } != null
                    it.copy(saved = saved)
                }
            }.collect { _listReddit }
        }
    }

    fun saveReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveEntityReddit(reddit)
        }
    }

    fun removeReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteEntityReddit(reddit.id)
        }
    }
}

inline fun <reified T : Any?> MutableStateFlow<T>.mutate(action: (T?) -> T?) {
    action(value)
}