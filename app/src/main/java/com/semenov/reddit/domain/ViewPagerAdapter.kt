package com.semenov.reddit.domain
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.semenov.reddit.MainFragment

class ViewPagerAdapter(fragmentActivity : FragmentActivity): FragmentStateAdapter(fragmentActivity) {
	override fun getItemCount(): Int {
		return 2
	}

	override fun createFragment(position: Int): Fragment {
		return MainFragment()

	}
}