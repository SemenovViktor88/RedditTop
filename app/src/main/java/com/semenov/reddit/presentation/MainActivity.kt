package com.semenov.reddit.presentation


import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semenov.reddit.R
import com.semenov.reddit.data.InstanceProvider

import com.semenov.reddit.data.model.ApiRedditChildren


import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.databinding.ActivityMainBinding
import com.semenov.reddit.domain.MyRedditAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var topApi: RedditApi
    lateinit var layoutManager: LinearLayoutManager
    private var adapter = MyRedditAdapter()




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        topApi = InstanceProvider.retrofitService

        startCoroutine()

        getAllMovieList()
    }
    private suspend fun startCoroutine() = lifecycleScope.async  {
        val result = topApi.getTopList()?.data?.children!!
        return@async result
    }.await()


    private fun getAllMovieList(job : List<ApiRedditChildren>) {
        adapter = MyRedditAdapter(job)
        adapter.notifyDataSetChanged()

    }
    private fun init(){
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
        }
    }
}
