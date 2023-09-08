package com.semenov.reddit.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class InfoViewModel: ViewModel() {

    private val repository = InstanceProvider.getRepository()
    private val _reddit: MutableStateFlow<Reddit> = MutableStateFlow(Reddit.createEmpty())
    val reddit = _reddit.asStateFlow()

    fun getRedditById(reddit: Reddit) {
        _reddit.value = reddit
    }

    fun saveReddit(reddit: Reddit) {
        _reddit.value = reddit.copy(saved = true)
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveReddit(reddit)
        }
    }

    fun removeReddit(reddit: Reddit) {
        _reddit.value = reddit.copy(saved = false)
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSavedReddit(reddit.id)
        }
    }
}