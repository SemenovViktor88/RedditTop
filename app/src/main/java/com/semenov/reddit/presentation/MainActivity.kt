package com.semenov.reddit.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenov.reddit.R
import com.semenov.reddit.data.InstanceProvider
import com.semenov.reddit.data.model.ApiReddit
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.data.model.ApiRedditPage
import com.semenov.reddit.data.model.RootList
import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.data.network.RetrofitHelper
import com.semenov.reddit.databinding.ActivityMainBinding
import com.semenov.reddit.domain.MyRedditAdapter
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var txt_name: TextView
        lateinit var txt_team: TextView
        lateinit var txt_createdby: TextView
        lateinit var image: ImageView

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topApi = InstanceProvider.retrofitService
        lifecycleScope.launch {
            val result = topApi.getTopList()
            Log.d("Mylog: ", result.toString())

            txt_name = findViewById(R.id.txt_name)
            txt_team = findViewById(R.id.txt_team)
            txt_createdby = findViewById(R.id.txt_createdby)
            image = findViewById(R.id.image_movie)

            val name = result?.data?.children!![1].data?.author
            val team = result.data.children[1].data?.subreddit
            val img = result.data.children[1].data?.thumbnail
            val createdby = result.data.children[1].data?.url

            txt_name.text = name
            txt_team.text = team
            txt_createdby.text = createdby

            Picasso.get().load(img).into(image)

            txt_createdby.setOnClickListener{
                // on below line we are opening a intent to view the url.
                val i = Intent(Intent.ACTION_VIEW)
                i.setData(Uri.parse(createdby))
                startActivity(i)
            }
        }
    }
}
