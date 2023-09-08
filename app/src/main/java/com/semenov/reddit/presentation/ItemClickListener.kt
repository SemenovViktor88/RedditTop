package com.semenov.reddit.presentation

import com.semenov.reddit.data.model.domain.Reddit

interface ItemClickListener {
    fun onItemClicked(reddit: Reddit)
    fun onSaveDeleteClicked(reddit: Reddit)
}