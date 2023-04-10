package com.semenov.reddit.presentation


import android.os.Bundle
import android.view.MenuItem

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenov.reddit.MainFragment
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
	lateinit var binding: FragmentMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		binding = FragmentMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		supportActionBar?.setDisplayHomeAsUpEnabled(true)
		supportFragmentManager.beginTransaction().replace(R.id.viewPager, MainFragment.newInstance()).commit()

	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == android.R.id.home) finish()
		return true
	}
}
