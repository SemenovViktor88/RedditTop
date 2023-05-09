package com.semenov.reddit.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.semenov.reddit.R
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.databinding.FragmentNewsBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.semenov.reddit.presentation.info.InfoRedditFragment

class NewsFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentNewsBinding
    private var adapter = NewsFragmentAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
        init()
        viewModel.loadTopList()
        viewModel.myLifeData.observe(viewLifecycleOwner) {
            getAllMovieList(it)
        }
        return binding.root
    }

    override fun onItemClicked() {
        if (isAdded()) {
            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.frameLayoutMainActivity, InfoRedditFragment.newInstance()).commit()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = NewsFragment()
    }

    private fun getAllMovieList(list: List<ApiRedditChildren>) {
        adapter.onNew(list)
    }

    private fun init() {
        binding.rcViewNewsFragment.adapter = adapter
    }
}