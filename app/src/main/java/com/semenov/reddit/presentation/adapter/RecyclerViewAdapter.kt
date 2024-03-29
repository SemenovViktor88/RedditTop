package com.semenov.reddit.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semenov.reddit.R
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.databinding.ItemLayoutBinding
import com.semenov.reddit.presentation.ItemClickListener
import com.squareup.picasso.Picasso

class RecyclerViewAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listReddit = mutableListOf<Reddit>()

    override fun getItemCount() = listReddit.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = listReddit[position]
        holder.bind(listItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLayoutBinding.bind(itemView)
        private val unixTime = System.currentTimeMillis() / 1000
        fun bind(listItem: Reddit) = with(binding) {
            author.text = listItem.author
            subredit.text = listItem.subreddit
            numComments.text = listItem.num_comments.toString()
            rating.text = listItem.ups.toString()
            created.text = ((unixTime - listItem.created) / 3600).toString()

            if (listItem.thumbnail.isEmpty()) image.setImageResource(R.drawable.photo)
            else Picasso.get().load(listItem.thumbnail).into(image)

            if (listItem.saved) saveButton.setColorFilter(Color.RED)
            else saveButton.setColorFilter(Color.WHITE)

            constrainlayout.setOnClickListener { listener.onItemClicked(listItem) }
            saveButton.setOnClickListener {
                listener.onSaveDeleteClicked(listItem)
            }
        }
    }

    fun newListReddit(list: List<Reddit>) {
        listReddit = list.toMutableList()
        notifyDataSetChanged()
    }
}