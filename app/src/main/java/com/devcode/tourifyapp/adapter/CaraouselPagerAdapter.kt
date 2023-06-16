package com.devcode.tourifyapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.data.remote.response.TravelBanner
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.databinding.ItemsCarouselBinding


class CaraouselPagerAdapter(private val listData: ArrayList<TravelBanner>, private val viewPager2 : ViewPager2) : RecyclerView.Adapter<CaraouselPagerAdapter.ViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    private val runnable = Runnable {
        listData.addAll(listData)
        notifyDataSetChanged()
    }
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
        val img = listData[position].image
        Glide.with(holder.itemView.context)
            .load(img)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.binding.ivSlider)
        if (position == listData.size - 1) {
            viewPager2.post(runnable)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<TravelBanner>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: String)
    }
}