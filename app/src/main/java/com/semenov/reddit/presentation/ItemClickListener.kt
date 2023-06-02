package com.semenov.reddit.presentation

import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.databinding.ItemLayoutBinding

interface ItemClickListener {
    fun onItemClicked()

    fun onSavedClicked(reddit: Reddit, binding: ItemLayoutBinding)
}