package com.devcode.tourifyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.ItemsCarouselBinding


class CaraouselPagerAdapter(private val listData:  MutableList<String>) : RecyclerView.Adapter<CaraouselPagerAdapter.ViewHolder>() {
    private lateinit var onItemClickCallback: CaraouselPagerAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: CaraouselPagerAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ViewHolder(val binding: ItemsCarouselBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = R.drawable.bg_post_image
        Glide.with(holder.itemView.context)
            .load(img)
            .placeholder(R.drawable.ic_placeholder_photo)
            .error(R.drawable.ic_placeholder_photo)
            .into(holder.binding.ivPlace)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataTravel: List<String>) {
        listData.clear()
        listData.addAll(dataTravel)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }
}