package com.semenov.reddit

import android.content.Context
import android.content.ContextParams
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.semenov.reddit.data.InstanceProvider
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.data.network.RedditApi
import com.semenov.reddit.databinding.FragmentMainBinding
import com.semenov.reddit.domain.ViewPagerAdapter
import com.semenov.reddit.presentation.MainActivity
import kotlinx.coroutines.launch
import java.text.FieldPosition

lateinit var layoutManager: LinearLayoutManager
class MainFragment : Fragment() {
	private val api= mutableListOf<ApiRedditChildren>()
	private lateinit var binding: FragmentMainBinding

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = FragmentMainBinding.inflate(inflater, container, false)
		parentFragmentManager.beginTransaction().replace(R.id.rcViewNewsFragment, NewsFragment.newInstance()).commit()
		init()
		return binding.root
	}

	private fun init() {
		binding.viewPager.adapter = MainActivity().adapter
	}


	companion object {

		@JvmStatic
		fun newInstance() = MainFragment()
	}
}