package com.semenov.reddit.presentation.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.semenov.reddit.R
import com.semenov.reddit.presentation.news.NewsFragment

class InfoRedditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_reddit, container, false)
    }
    companion object {

        @JvmStatic
        fun newInstance() = InfoRedditFragment()
    }
}