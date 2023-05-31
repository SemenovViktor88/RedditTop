package com.semenov.reddit.presentation.save

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    val liveData: MutableLiveData<List<Reddit>> = MutableLiveData()
    private val repository = InstanceProvider.getRepository()

    fun loadList() = viewModelScope.launch {
        val result = repository.loadTopList()
        liveData.postValue(result)
    }
}