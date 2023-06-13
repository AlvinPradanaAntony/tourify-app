package com.devcode.tourifyapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.data.remote.response.SearchItem
import com.devcode.tourifyapp.databinding.ItemsPlaceBinding
import com.devcode.tourifyapp.ui.detail.DetailActivity

class TravelPlaceSearchAdapter(private val listData: List<SearchItem>): RecyclerView.Adapter<TravelPlaceSearchAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemsPlaceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = listData[position].name
        val img = listData[position].picture
        holder.binding.tvPlaceName.text = name
        Glide.with(holder.itemView.context)
            .load(img)
            .placeholder(R.drawable.ic_placeholder_photo)
            .error(R.drawable.ic_placeholder_photo)
            .into(holder.binding.ivPlace)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("id", listData[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }
}