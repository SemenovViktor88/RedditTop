package com.semenov.reddit.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.semenov.reddit.presentation.MainFragment
import com.semenov.reddit.presentation.NewsFragment
import com.semenov.reddit.presentation.SaveFragment

class ViewPagerAdapter(fragmentActivity : FragmentActivity, private val list: List<Fragment>): FragmentStateAdapter(fragmentActivity) {
	override fun getItemCount(): Int {
		return list.size
	}

	override fun createFragment(position: Int): Fragment {
		return list[position]
	}
}