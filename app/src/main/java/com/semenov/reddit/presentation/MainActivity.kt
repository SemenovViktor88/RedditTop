package com.semenov.reddit.presentation


import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenov.reddit.MainFragment
import com.semenov.reddit.R
import com.semenov.reddit.data.InstanceProvider

import com.semenov.reddit.data.model.ApiRedditChildren


import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.databinding.ActivityMainBinding
import com.semenov.reddit.domain.MyRedditAdapter
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
//    lateinit var topApi: RedditApi
//    private var adapter = MyRedditAdapter()
//    private val myLifeData = MutableLiveData<List<ApiRedditChildren>>()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainFragment.newInstance()).commit()


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return true
    }
}
