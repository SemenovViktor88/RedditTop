package com.semenov.reddit.data.presentation

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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

        binding.btn.setOnClickListener {
            lifecycleScope.launch {
                val result = topApi.getTopList()
                Log.d("Mylog: ", result.toString())
                binding.textView.text = result.toString()
            }
        }

    }
}