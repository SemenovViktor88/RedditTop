<<<<<<<< HEAD:app/src/main/java/com/semenov/reddit/presentation/ViewPagerAdapter.kt
package com.semenov.reddit.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.semenov.reddit.presentation.MainFragment
import com.semenov.reddit.presentation.NewsFragment
import com.semenov.reddit.presentation.SaveFragment
========
package com.semenov.reddit.presentation.main
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
>>>>>>>> 8233821 (create click listener for open info news):app/src/main/java/com/semenov/reddit/presentation/main/ViewPagerAdapter.kt

class ViewPagerAdapter(fragmentActivity : FragmentActivity, private val list: List<Fragment>): FragmentStateAdapter(fragmentActivity) {
	override fun getItemCount(): Int {
		return list.size
	}

	override fun createFragment(position: Int): Fragment {
		return list[position]
	}
}