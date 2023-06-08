package com.devcode.tourifyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.ItemsCarouselBinding


class CaraouselPagerAdapter(private val context: Context) : RecyclerView.Adapter<CaraouselPagerAdapter.ViewHolder>() {

    private var list = mutableListOf<String>()
    private lateinit var onItemClickCallback: CaraouselPagerAdapter.OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: CaraouselPagerAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class ViewHolder(val binding: ItemsCarouselBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsCarouselBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = list[position]
        Glide.with(holder.itemView.context)
            .load(img)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.ivSlider)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataTravel: List<String>) {
        this.list.clear()
        this.list.addAll(dataTravel)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }
}