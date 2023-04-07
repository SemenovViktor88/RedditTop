package com.semenov.reddit.presentation


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenov.reddit.data.InstanceProvider

import com.semenov.reddit.data.model.ApiRedditChildren


import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.databinding.ActivityMainBinding
import com.semenov.reddit.domain.MyRedditAdapter
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var topApi: RedditApi
    private var adapter = MyRedditAdapter()
    private val myLifeData = MutableLiveData<List<ApiRedditChildren>>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        topApi = InstanceProvider.retrofitService

        loadTopList()
        myLifeData.observe(this) {
            getAllMovieList(it)
        }
    }

    private fun loadTopList() = lifecycleScope.launch {
        val result = topApi.getTopList()?.data?.item!!
        myLifeData.postValue(result)
    }


    private fun getAllMovieList(list: List<ApiRedditChildren>) {
        adapter.onNewData(list)
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
        }
    }
}
