package com.semenov.reddit.presentation.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.semenov.reddit.R
import com.semenov.reddit.data.model.db.EntityReddit
import com.semenov.reddit.data.model.db.toDomainModel
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.databinding.FragmentNewsBinding
import com.semenov.reddit.databinding.FragmentSaveBinding
import com.semenov.reddit.databinding.ItemLayoutBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.semenov.reddit.presentation.info.InfoFragment
import com.semenov.reddit.presentation.news.NewsViewModel
import com.semenov.reddit.presentation.news.RcAdapterNews
import kotlinx.coroutines.launch

class SaveFragment : Fragment(), ItemClickListener {

	private lateinit var binding: FragmentSaveBinding
	private lateinit var viewModel: SaveViewModel
	private var adapter = RcAdapterSave(this)

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSaveBinding.inflate(inflater, container, false)
		init()
		return binding.root
	}

	override fun onItemClicked() {
		if (isAdded) {
			parentFragmentManager.beginTransaction().addToBackStack(null)
				.replace(R.id.frameLayoutMainActivity, InfoFragment.newInstance()).commit()
		}
	}

	override fun onSavedClicked(reddit: Reddit, binding: ItemLayoutBinding) {
//        binding.floatingActionButton.setColorFilter(R.color.error)
		lifecycleScope.launch { viewModel.deleteRedditDataBase(reddit) }
	}

	private fun init() {
		viewModel = ViewModelProvider(this)[SaveViewModel::class.java]
		binding.rcViewSaveFragment.adapter = adapter
		viewModel.getListEntityRedditVM()
		viewModel.listEntityRedditliveData.observe(viewLifecycleOwner) {
			getAllMovieList(it)
		}
	}
	private fun getAllMovieList(list: List<Reddit>) {
		adapter.onNew(list)
	}
}