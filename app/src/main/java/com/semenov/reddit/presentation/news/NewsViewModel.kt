package com.semenov.reddit.presentation.news

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.NewsReddit
import com.semenov.reddit.database.getDatabase
import com.semenov.reddit.repository.NewsRepository
import kotlinx.coroutines.launch

class NewsViewModel(application: Application) : ViewModel() {

    val liveData: MutableLiveData<List<NewsReddit>> = MutableLiveData()
    val repository = NewsRepository(getDatabase(application))

    fun loadList()= viewModelScope.launch {
        val result = repository.loadTopList()
        liveData.postValue(result)
    }
}