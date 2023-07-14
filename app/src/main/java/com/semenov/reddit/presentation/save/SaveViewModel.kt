package com.semenov.reddit.presentation.save

import androidx.lifecycle.*
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    private val repository = InstanceProvider.getRepository()

    fun getListEntityRedditVM() = repository.listRedditInDBStateFlow

    fun deleteReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteRedditDB(reddit.id) }
    }
}