package com.semenov.reddit.presentation.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.R
import com.semenov.reddit.databinding.FragmentNewsBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.semenov.reddit.presentation.adapter.RecyclerViewAdapter
import com.semenov.reddit.presentation.info.InfoFragment
import kotlinx.coroutines.launch

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         viewLifecycleOwner.lifecycleScope.launch {
             repeatOnLifecycle(Lifecycle.State.STARTED) {
                 viewModel.getListRedditVM().collect{
                     adapter.newListReddit(viewModel.listRedditLiveData.value)
                 }
             }
         }
    }

    override fun onItemClicked() {
        if (isAdded) {
            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.frameLayoutMainActivity, InfoFragment.newInstance()).commit()
        }
    }

    override fun onSaveDeleteClicked(reddit: Reddit) {
        when(reddit.saved){
            true ->  viewModel.removeReddit(reddit)
            false -> viewModel.saveReddit(reddit)
        }
    }
}