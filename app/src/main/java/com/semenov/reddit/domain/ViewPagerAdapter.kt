package com.semenov.reddit.domain
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.semenov.reddit.MainFragment
import com.semenov.reddit.NewsFragment
import com.semenov.reddit.SaveFragment

class ViewPagerAdapter(fragmentActivity : FragmentActivity, private val list: List<Fragment>): FragmentStateAdapter(fragmentActivity) {
	override fun getItemCount(): Int {
		return list.size
	}

	override fun createFragment(position: Int): Fragment {
		return list[position]
	}
}