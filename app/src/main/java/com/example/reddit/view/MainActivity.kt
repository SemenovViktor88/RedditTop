package com.example.reddit.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.reddit.databinding.ActivityMainBinding
import com.example.reddit.model.Common
import com.example.reddit.model.RedditTop
import retrofit2.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val topApi = Common.retrofitService


       Log.d("Mylog:", topApi.toString())

    }
}
