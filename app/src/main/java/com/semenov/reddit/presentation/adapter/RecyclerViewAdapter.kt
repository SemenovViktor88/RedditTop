package com.semenov.reddit.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semenov.reddit.data.model.domain.Reddit
import com.semenov.reddit.R
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
        fun bind(listItem: Reddit) = with(binding) {
            name.text = listItem.author
            title.text = listItem.title
            numComments.text = listItem.num_comments.toString()
            Picasso.get().load(listItem.thumbnail).into(image)
            constrainlayout.setOnClickListener { listener.onItemClicked() }
            floatingActionButton.setOnClickListener {
                listener.onSaveDeleteClicked(listItem)
            }
        }
    }

    fun newListReddit(list: List<Reddit>) {
        listReddit.clear()
        listReddit.addAll(list)
        notifyDataSetChanged()
    }

    fun removeItem(reddit: Reddit) {
        listReddit.remove(reddit)
        notifyDataSetChanged()
    }
}