package com.example.reddit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.reddit.databinding.ActivityMainBinding
import com.example.reddit.model.RedditTop
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val to100 = "top.json?"
        val filter = "limit100"


    }
    private val redditApi: RedditApi
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        redditApi = retrofit.create(RedditApi::class.java)
    }
    fun getNews(after: String, limit: String): Call<RedditTop>{
        return redditApi.getTop(after, limit)
    }

}

interface RedditApi{
    @GET("/top.json")
    fun getTop(@Query("after") after: String,
               @Query("limit") limit: String)
            : Call<RedditTop>
}
