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
import com.semenov.reddit.databinding.ItemLayoutBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.semenov.reddit.presentation.info.InfoFragment
import com.semenov.reddit.presentation.adapter.RecyclerViewAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class SaveFragment : Fragment(), ItemClickListener {

	private lateinit var binding: FragmentSaveBinding
	private lateinit var viewModel: SaveViewModel
	private var adapter = RecyclerViewAdapter(this)
	private lateinit var listReddit : List<Reddit>

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSaveBinding.inflate(inflater, container, false)
		viewModel = ViewModelProvider(this)[SaveViewModel::class.java]
		init()
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
		val job = lifecycleScope.launch(Dispatchers.IO) { viewModel.deleteRedditDataBase(reddit) }
		if (job.isCompleted){
				adapter.notifyItemRemoved(listReddit.indexOf(reddit))
				adapter.notifyItemRangeChanged(listReddit.indexOf(reddit), listReddit.size)
				adapter.notifyDataSetChanged()
		}
	}

	private fun init() {
		binding.rcViewSaveFragment.adapter = adapter
		viewModel.getListEntityRedditVM()
		viewModel.listRedditLiveData.observe(viewLifecycleOwner) {
			listReddit = it
			getAllMovieList(listReddit)
		}
	}
	private fun getAllMovieList(list: List<Reddit>) {
		adapter.newListReddit(list)
	}
}