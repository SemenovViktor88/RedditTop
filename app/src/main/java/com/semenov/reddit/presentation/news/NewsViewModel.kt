package com.semenov.reddit.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    val liveData: MutableLiveData<List<Reddit>> = MutableLiveData()
    private val repository = InstanceProvider.getRepository()

    fun getListRedditVM() = viewModelScope.launch {
        val result = repository.getListRedditRepository()
        liveData.postValue(result)
    }
}