package com.semenov.reddit.presentation.save

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    private val repository = InstanceProvider.getRepository()

    fun getListEntityReddit() =
        repository.getAllEntityReddit().stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun deleteReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteEntityReddit(reddit.id) }
    }
}