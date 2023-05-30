package com.semenov.reddit.presentation.save

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.semenov.reddit.R
import com.semenov.reddit.databinding.FragmentSaveBinding
import com.semenov.reddit.presentation.SaveClickListener
import com.semenov.reddit.presentation.news.RcAdapter

class SaveFragment : Fragment(), SaveClickListener {

	private lateinit var binding: FragmentSaveBinding
	private lateinit var viewModel: SaveViewModel
	private var adapter = RcAdapter()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_save, container, false)
	}

	override fun onItemClicked() {
		TODO("Not yet implemented")
	}
}