package com.semenov.reddit.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
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
            _listReddit.combine(getListEntityReddit()) { listReddit, _listEntityReddit ->
                _listReddit.value = listReddit.map {
                    val saved = _listEntityReddit.find { reddit ->
                        reddit.id == it.id
                    } != null
                    it.copy(saved = saved)
                }
            }.collect { _listReddit }
        }
    }

    private fun getListEntityReddit () = repository.getAllEntityReddit().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

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