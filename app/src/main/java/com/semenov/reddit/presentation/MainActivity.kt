package com.semenov.reddit.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenov.reddit.data.InstanceProvider
import com.semenov.reddit.data.model.ApiReddit
import com.semenov.reddit.data.model.RootList
import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.databinding.ActivityMainBinding
import com.semenov.reddit.domain.MyRedditAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var mService: RedditApi
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: MyRedditAdapter
//    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val topApi = InstanceProvider.retrofitService
//        lifecycleScope.launch {
//            val result = topApi.getTopList()
//            Log.d("Mylog: ", result.toString())
//        }
        mService = InstanceProvider.retrofitService
        binding.recyclerList.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerList.layoutManager = layoutManager
//        dialog = SpotsDialog().Builder().setCancelable(true).setContext(this).build()

        getAllMovieList()
    }

    private fun getAllMovieList() {
//        dialog.show()
        mService.getTopList().enqueue(object : Callback<MutableList<RootList>> {
            override fun onFailure(call: Call<MutableList<RootList>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<RootList>>, response: Response<MutableList<RootList>>) {
                adapter = MyRedditAdapter(baseContext, response.body() as MutableList<ApiReddit>)
                adapter.notifyDataSetChanged()
                binding.recyclerList.adapter = adapter
//                dialog.dismiss()
            }
        })
    }
}