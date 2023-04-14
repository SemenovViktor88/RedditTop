package com.semenov.reddit.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semenov.reddit.NewsFragment
import com.semenov.reddit.R
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.databinding.ItemLayoutBinding
import com.semenov.reddit.presentation.InfoRedditFragment

import com.squareup.picasso.Picasso

class NewsFragmentAdapter: RecyclerView.Adapter<NewsFragmentAdapter.MyViewHolder>() {
    private val api= mutableListOf<ApiRedditChildren>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = ItemLayoutBinding.bind(itemView)
        fun bind(listItem: ApiRedditChildren) = with(binding) {
            name.text = listItem.data?.author
            title.text = listItem.data?.title
            numComments.text = listItem.data?.num_comments.toString()
            Picasso.get().load(listItem.data?.thumbnail).into(image)
            image.setOnClickListener {
                InfoRedditFragment.newInstance()
           }
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
    fun onNew(list : List<ApiRedditChildren>){
        api.addAll(list)
        notifyDataSetChanged()
    }
}