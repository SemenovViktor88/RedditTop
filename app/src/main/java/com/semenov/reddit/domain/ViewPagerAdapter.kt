package com.semenov.reddit.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.semenov.reddit.NewsFragment
import com.semenov.reddit.R
import com.semenov.reddit.SaveFragment
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso

class ViewPagerAdapter(fragmentActivity : FragmentActivity): FragmentStateAdapter(fragmentActivity) {
	override fun getItemCount(): Int {
		return 2
	}

	override fun createFragment(position: Int): Fragment {
		return when (position){
			0 -> NewsFragment()
			else -> SaveFragment()
		}
	}
}