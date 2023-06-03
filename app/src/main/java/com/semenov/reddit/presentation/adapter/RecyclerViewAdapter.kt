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

class RecyclerViewAdapter(private val listener: ItemClickListener): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private val api= mutableListOf<Reddit>()
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemLayoutBinding.bind(itemView)
        fun bind(listItem: Reddit,) = with(binding) {
            name.text = listItem.author
            title.text = listItem.title
            numComments.text = listItem.num_comments.toString()
            Picasso.get().load(listItem.thumbnail).into(image)
            constrainlayout.setOnClickListener { listener.onItemClicked() }
            floatingActionButton.setOnClickListener { listener.onSaveDeleteClicked(listItem, binding) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = api.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = api[position]
        holder.bind(listItem)
    }

    fun onNew(list : List<Reddit>){
        api.addAll(list)
        notifyDataSetChanged()
    }
}