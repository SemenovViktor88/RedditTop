package com.semenov.reddit.domain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semenov.reddit.R
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.databinding.ItemLayoutBinding

import com.squareup.picasso.Picasso

class NewsFragmentAdapter: RecyclerView.Adapter<NewsFragmentAdapter.MyViewHolder>() {
    private val api= mutableListOf<ApiRedditChildren>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
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