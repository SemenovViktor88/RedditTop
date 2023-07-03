package com.semenov.reddit.presentation.save

import androidx.lifecycle.*
import com.semenov.reddit.InstanceProvider
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.presentation.news.mutate
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SaveViewModel : ViewModel() {

    private val repository = InstanceProvider.getRepository()
    val listRedditLiveData: StateFlow<List<Reddit>>
        get() = _listRedditLiveData
    private val _listRedditLiveData: MutableStateFlow<List<Reddit>> = MutableStateFlow()


    fun getListEntityRedditVM() = repository.getAllRedditDB().onEach{ _listRedditLiveData.emit(it) }.launchIn(viewModelScope)



    fun deleteReddit(reddit: Reddit) {
        viewModelScope.launch {
            repository.deleteRedditDB(reddit.id)
//            _listRedditLiveData.drop(_listRedditLiveData.value.indexOf(reddit))
        }
    }
}