package com.semenov.reddit.presentation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.semenov.reddit.R
import com.semenov.reddit.databinding.FragmentMainBinding
import com.semenov.reddit.presentation.adapter.ViewPagerAdapter
import com.semenov.reddit.presentation.news.NewsFragment
import com.semenov.reddit.presentation.save.SaveFragment

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val fragList: List<Fragment> = listOf(
        NewsFragment(),
        SaveFragment(),
    )
    lateinit var navView: BottomNavigationView
    private lateinit var viewPager: ViewPager2
    private lateinit var adapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = ViewPagerAdapter(requireActivity(), fragList)
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