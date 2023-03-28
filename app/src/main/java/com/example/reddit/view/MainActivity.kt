package com.example.reddit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.reddit.databinding.ActivityMainBinding
import com.example.reddit.model.RedditApi
import com.example.reddit.model.RedditTop
import com.example.reddit.model.RetrofitHelper
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.moshi.MoshiConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quotesApi = RetrofitHelper.getInstance().create(RedditApi::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            val result = quotesApi.getTop()
            if (result != null)
            // Checking the results
                Log.d("Mylog: ", result.toString())
        }
    }
}
