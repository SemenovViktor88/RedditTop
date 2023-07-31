package com.semenov.reddit.presentation.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.semenov.reddit.R
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.databinding.FragmentSaveBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.semenov.reddit.presentation.adapter.RecyclerViewAdapter
import com.semenov.reddit.presentation.info.InfoFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SaveFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentSaveBinding
    private lateinit var viewModel: SaveViewModel
    private var adapter = RecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSaveBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SaveViewModel::class.java]
        binding.rcViewSaveFragment.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getListEntityReddit()
            .onEach { adapter.newListReddit(it) }
            .launchIn(lifecycleScope)
    }

    override fun onItemClicked() {
        if (isAdded) {
            parentFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.frameLayoutMainActivity, InfoFragment.newInstance()).commit()
        }
    }

    override fun onSaveDeleteClicked(reddit: Reddit) {
        viewModel.deleteReddit(reddit)
    }
}