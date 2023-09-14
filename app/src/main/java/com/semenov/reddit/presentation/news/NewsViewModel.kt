package com.semenov.reddit.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private var token: String = ""
    val listReddit: StateFlow<List<Reddit>>
        get() = _listReddit
    private val _listReddit: MutableStateFlow<List<Reddit>> = MutableStateFlow(emptyList())
    private val repository by lazy { InstanceProvider.getRepository() }

    fun saveReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveReddit(reddit)
        }
    }

    fun removeReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSavedReddit(reddit.id)
        }
    }

    fun loadInitial() {
        loadReddits().invokeOnCompletion {
            it ?: subscribeOnSavedReddits()
        }
    }

    fun loadNext() {
        token.takeUnless { it.isEmpty() }?.let(::loadReddits)
    }

    private fun loadReddits(after: String = "") = viewModelScope.launch {
        val page = repository.getApiReddit(after)
        _listReddit.value = page.items
        token = page.token
    }

    private fun subscribeOnSavedReddits() {
        repository.getAllSavedReddit().onEach { savedList ->
            _listReddit.value = _listReddit.value.map { reddit ->
                val saved = savedList.find { savedReddit ->
                    savedReddit.id == reddit.id
                } != null
                reddit.copy(saved = saved)
            }
        }.launchIn(viewModelScope)
    }
}