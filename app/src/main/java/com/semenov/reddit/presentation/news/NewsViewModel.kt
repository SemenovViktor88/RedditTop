package com.semenov.reddit.presentation.news

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.database.getDatabase
import com.semenov.reddit.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    val liveData: MutableLiveData<List<Reddit>> = MutableLiveData()
    private val repository = NewsRepository(getDatabase(application))

    fun loadList()= viewModelScope.launch {
        val result = repository.loadTopList()
        liveData.postValue(result)
    }
}