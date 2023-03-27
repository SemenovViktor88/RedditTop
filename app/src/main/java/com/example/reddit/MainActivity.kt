package com.example.reddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reddit.databinding.ActivityMainBinding
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val to100 = "top.json?"
        val filter = "limit100"

        var retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com/$to100$filter")
            .addConverterFactory(GsonConverterFactory.create())
            .build()



    }

}