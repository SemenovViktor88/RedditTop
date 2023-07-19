package com.semenov.reddit.presentation.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.semenov.reddit.R
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.databinding.FragmentSaveBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.semenov.reddit.presentation.adapter.RecyclerViewAdapter
import com.semenov.reddit.presentation.info.InfoFragment
import kotlinx.coroutines.launch

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
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getListEntityReddit().collect {
                    adapter.newListReddit(it) }
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
        viewModel.deleteReddit(reddit)
    }
}