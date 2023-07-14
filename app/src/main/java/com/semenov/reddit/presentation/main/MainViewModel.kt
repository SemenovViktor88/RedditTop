package com.semenov.reddit.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.semenov.reddit.InstanceProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = InstanceProvider.getRepository()

    fun deleteAll () {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteAllDB() }
    }
}