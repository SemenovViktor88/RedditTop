package com.semenov.reddit.presentation.save

import android.annotation.SuppressLint
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
import com.semenov.reddit.databinding.ItemLayoutBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.semenov.reddit.presentation.info.InfoFragment
import com.semenov.reddit.presentation.adapter.RecyclerViewAdapter
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
		init()
		viewModel.getListEntityRedditVM()
		viewModel.listRedditLiveData.observe(viewLifecycleOwner) {
			getAllMovieList(it)
		}
		return binding.root
	}

	override fun onItemClicked() {
		if (isAdded) {
			parentFragmentManager.beginTransaction().addToBackStack(null)
				.replace(R.id.frameLayoutMainActivity, InfoFragment.newInstance()).commit()
		}
	}

	override fun onSaveDeleteClicked(reddit: Reddit, binding: ItemLayoutBinding) {
//        binding.floatingActionButton.setColorFilter(R.color.error)
		adapter.removeItem(reddit)
		val job = lifecycleScope.launch { viewModel.deleteRedditDataBase(reddit) }
		if (job.isCompleted){
			viewModel.getListEntityRedditVM()
			viewModel.listRedditLiveData.observe(viewLifecycleOwner) {
				adapter.notifyItemRemoved(it.indexOf(reddit))
				adapter.notifyItemRangeChanged(it.indexOf(reddit), it.size)
				adapter.notifyDataSetChanged()
			}
		}
	}

	private fun init() {
		binding.rcViewSaveFragment.adapter = adapter
	}
	private fun getAllMovieList(list: List<Reddit>) {
		adapter.newListReddit(list)
	}
}