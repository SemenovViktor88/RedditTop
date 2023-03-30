package com.semenov.reddit.data.presentation

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import com.semenov.reddit.data.model.Common
import com.semenov.reddit.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val topApi = Common.retrofitService



        // launching a new coroutine
        GlobalScope.launch {
            val result = topApi.getTopList()

            if (result != null)
            // Checking the results
                Log.d("Mylog: ", result.toString())
        }
    }
}