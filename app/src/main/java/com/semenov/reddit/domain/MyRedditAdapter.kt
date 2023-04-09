package com.semenov.reddit.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semenov.reddit.R
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.data.model.ApiRedditPage
import com.semenov.reddit.databinding.ItemLayoutBinding
import com.semenov.reddit.presentation.MainActivity
import com.squareup.picasso.Picasso


class MyRedditAdapter : RecyclerView.Adapter<MyRedditAdapter.MyViewHolder>() {
    private val apiReddits= mutableListOf<ApiRedditChildren>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemLayoutBinding.bind(itemView)
        fun bind(listItem: ApiRedditChildren) = with(binding) {
            txtName.text = listItem.data?.author
            txtTeam.text = listItem.data?.title
            txtCreatedby.text = listItem.data?.url
            Picasso.get().load(listItem.data?.thumbnail).into(imageMovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = apiReddits[position]
        holder.bind(listItem)
    }

    override fun getItemCount() = apiReddits.size

    fun onNewData(list: List<ApiRedditChildren>){
        apiReddits.addAll(list)
        notifyDataSetChanged()
    }
}