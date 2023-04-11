package com.semenov.reddit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.semenov.reddit.databinding.FragmentMainBinding
import com.semenov.reddit.domain.ViewPagerAdapter
import com.semenov.reddit.presentation.MainActivity

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val fragList = listOf(
        NewsFragment.newInstance(),
        SaveFragment.newInstance()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        init()
        return binding.root
    }

    private fun init() {
        val adapter = ViewPagerAdapter(activity as MainActivity, fragList)
        binding.viewPager.adapter = adapter
    }


    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}