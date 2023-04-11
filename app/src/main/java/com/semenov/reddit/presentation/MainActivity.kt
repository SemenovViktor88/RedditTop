package com.semenov.reddit.presentation


import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenov.reddit.MainFragment
import com.semenov.reddit.NewsFragment
import com.semenov.reddit.R
import com.semenov.reddit.data.InstanceProvider

import com.semenov.reddit.data.model.ApiRedditChildren


import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.databinding.ActivityMainBinding
import com.semenov.reddit.databinding.FragmentMainBinding
import com.semenov.reddit.domain.MyRedditAdapter
import com.semenov.reddit.domain.ViewPagerAdapter
import kotlinx.coroutines.launch



class MainActivity : AppCompatActivity() {
	val adapter = ViewPagerAdapter(this)
	lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		supportFragmentManager.beginTransaction().
			replace(R.id.frameLayoutMainActivity, NewsFragment.newInstance()).commit()
	}
}
