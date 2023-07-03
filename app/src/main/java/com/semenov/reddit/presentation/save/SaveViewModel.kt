package com.semenov.reddit.presentation.save

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.presentation.news.mutate
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    val listRedditLiveData: StateFlow<List<Reddit>>
        get() = _listRedditLiveData
    private val _listRedditLiveData: MutableStateFlow<List<Reddit>> = MutableStateFlow(emptyList())
    private val repository = InstanceProvider.getRepository()

    fun getListEntityRedditVM() {
        viewModelScope.launch {
            repository.getAllRedditDB().collect{
            _listRedditLiveData.value = it
            }
        }
    }


    fun deleteReddit(reddit: Reddit) {
        viewModelScope.launch {
            repository.deleteRedditDB(reddit.id)
//            _listRedditLiveData.drop(_listRedditLiveData.value.indexOf(reddit))
        }
    }
}