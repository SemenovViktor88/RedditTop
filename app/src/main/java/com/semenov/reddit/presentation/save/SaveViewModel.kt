package com.semenov.reddit.presentation.save

import androidx.lifecycle.*
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.db.toDomainModel
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.presentation.news.mutate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    private val repository = InstanceProvider.getRepository()
    val listRedditLiveData: StateFlow<List<Reddit>>
        get() = _listRedditLiveData
    private val _listRedditLiveData: MutableStateFlow<List<Reddit>> = MutableStateFlow(emptyList())


    fun getListEntityRedditVM() {
        viewModelScope.launch(Dispatchers.IO) {
            _listRedditLiveData.value = repository.getAllRedditDB().toDomainModel()
            }
        }


    fun deleteReddit(reddit: Reddit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteRedditDB(reddit.id)
            _listRedditLiveData.mutate {
                it?.toMutableList()?.apply { remove(reddit) }
            }
        }
    }
}