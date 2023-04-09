package com.semenov.reddit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.semenov.reddit.data.InstanceProvider
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.databinding.FragmentMainBinding
import com.semenov.reddit.domain.ViewPagerAdapter
import kotlinx.coroutines.launch


class MainFragment : Fragment() {
	private val api= mutableListOf<ApiRedditChildren>()
	private lateinit var binding: FragmentMainBinding

	lateinit var topApi: RedditApi
	private var adapter = ViewPagerAdapter()
	private val myLifeData = MutableLiveData<List<ApiRedditChildren>>()


	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentMainBinding.inflate(inflater, container, false)

		init()
		topApi = InstanceProvider.retrofitService
		loadTopList()
		myLifeData.observe(viewLifecycleOwner) {
			getAllMovieList(it)
		}


		return binding.root
	}


	companion object {

		@JvmStatic
		fun newInstance() = MainFragment()
	}


	private fun loadTopList() = lifecycleScope.launch {
		val result = topApi.getTopList()?.data?.item!!
		myLifeData.postValue(result)
	}


	private fun getAllMovieList(list: List<ApiRedditChildren>) {
		adapter.onNew(list)
	}

	private fun init() {

		binding.placeHolder.adapter = adapter

	}
}