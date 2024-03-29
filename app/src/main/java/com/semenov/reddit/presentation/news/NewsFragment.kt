package com.semenov.reddit.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semenov.reddit.R
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.databinding.FragmentNewsBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.semenov.reddit.presentation.adapter.RecyclerViewAdapter
import com.semenov.reddit.presentation.info.InfoFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class NewsFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    private var adapter = RecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        binding.rcViewNewsFragment.adapter = adapter
        viewModel.loadInitial()
        binding.rcViewNewsFragment.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if(!viewModel.isLoading){
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                        viewModel.loadNext()
                    }
                }
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.listReddit
            .flowWithLifecycle(lifecycle)
            .onEach { adapter.newListReddit(it) }
            .launchIn(lifecycleScope)
    }

    override fun onItemClicked(reddit: Reddit) {
        if (isAdded) {
            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.frameLayoutMainActivity, InfoFragment.newInstance(reddit)).commit()
        }
    }
    override fun onSaveDeleteClicked(reddit: Reddit) {
        when (reddit.saved) {
            true -> viewModel.removeReddit(reddit)
            false -> viewModel.saveReddit(reddit)
        }
    }
}