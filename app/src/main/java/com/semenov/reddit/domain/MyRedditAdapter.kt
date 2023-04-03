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

class MyRedditAdapter(private val context: Context, private val apiReddits: MutableList<ApiReddit>):
    RecyclerView.Adapter<MyRedditAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val image: ImageView = itemView.findViewById(R.id.image_movie)
        val txt_name: TextView = itemView.findViewById(R.id.txt_name)
        val txt_team: TextView = itemView.findViewById(R.id.txt_team)
        val txt_createdby: TextView = itemView.findViewById(R.id.txt_createdby)

        fun bind(listItem: ApiReddit) {
            image.setOnClickListener {
                Toast.makeText(it.context, "нажал на $image", Toast.LENGTH_SHORT)
                    .show()
            }
            itemView.setOnClickListener {
                Toast.makeText(it.context, "нажал на ${txt_name.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = apiReddits.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = apiReddits[position]
        holder.bind(listItem)

        Picasso.get().load(apiReddits[position].imageurl).into(holder.image)
        holder.txt_name.text = apiReddits[position].name
        holder.txt_team.text = apiReddits[position].team
        holder.txt_createdby.text = apiReddits[position].createdby
    }

}