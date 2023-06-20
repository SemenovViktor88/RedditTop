package com.semenov.reddit

import android.content.Context
import androidx.room.Room
import com.semenov.reddit.data.database.RedditDatabase
import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.data.network.RetrofitHelper
import com.semenov.reddit.data.repository.RedditRepository
import com.semenov.reddit.data.repository.RedditRepositoryImpl

object InstanceProvider {
    private const val BASE_URL = "https://www.reddit.com/"
    private val retrofitService: RedditApi = RetrofitHelper.getClient(BASE_URL).create(RedditApi::class.java)

    private var repository: RedditRepository? = null
    private lateinit var database: RedditDatabase

    fun init(context: Context) {
        database = Room
            .databaseBuilder(context.applicationContext, RedditDatabase::class.java, "reddit")
            .build()
    }

    fun getRepository(): RedditRepository {
        synchronized(RedditRepository::class.java) {
            return repository ?: RedditRepositoryImpl(retrofitService, database)
        }
    }
}