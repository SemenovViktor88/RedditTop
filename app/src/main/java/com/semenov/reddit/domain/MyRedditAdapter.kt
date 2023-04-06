package com.semenov.reddit.domain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.semenov.reddit.R
import com.semenov.reddit.data.model.ApiReddit
import com.semenov.reddit.data.model.ApiRedditChildren
import com.semenov.reddit.data.model.RootList
import com.semenov.reddit.databinding.ItemLayoutBinding
import com.squareup.picasso.Picasso


class MyRedditAdapter(/*private val context: Context, */private val apiReddits: List<ApiRedditChildren>) : RecyclerView.Adapter<MyRedditAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
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
}