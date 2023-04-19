package com.semenov.reddit

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.semenov.reddit.databinding.FragmentMainBinding
import com.semenov.reddit.domain.ViewPagerAdapter
import com.semenov.reddit.presentation.MainActivity

class MainFragment : Fragment() {
	private lateinit var binding: FragmentMainBinding
	private val fragList = listOf(
		NewsFragment.newInstance(),
		SaveFragment.newInstance(),
	)
	lateinit var navView: BottomNavigationView
	private lateinit var viewPager: ViewPager2
	private lateinit var adapter: ViewPagerAdapter
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		val activity = activity ?: return null
		adapter = ViewPagerAdapter(activity, fragList)
		binding = FragmentMainBinding.inflate(inflater, container, false)
		navView = binding.bottomNavigationView
		viewPager = binding.viewPager2

		init()
		viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
			override fun onPageSelected(position: Int) {
				navView.menu.getItem(position).isChecked = true
			}
		})
		navView.setOnItemSelectedListener {
			when (it.itemId) {
				R.id.actionSearch -> {
					viewPager.currentItem = 0
					true
				}
				R.id.actionSaved -> {
					viewPager.currentItem = 1
					true
				}
				else -> false
			}
		}
		return binding.root
	}

	private fun init() {

		binding.viewPager2.adapter = adapter
	}


	companion object {
		@JvmStatic
		fun newInstance() = MainFragment()
	}
}