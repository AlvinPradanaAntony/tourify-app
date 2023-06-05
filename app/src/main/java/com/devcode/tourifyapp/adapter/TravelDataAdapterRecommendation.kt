package com.devcode.tourifyapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.databinding.ItemsRecommendationTravelBinding

class TravelDataAdapterRecommendation(private val listData: ArrayList<TravelDataDummyResponse>): RecyclerView.Adapter<TravelDataAdapterRecommendation.ViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    class ViewHolder(var binding: ItemsRecommendationTravelBinding) : RecyclerView.ViewHolder(binding.root)

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsRecommendationTravelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = listData[position].name
        val img = listData[position].image
        holder.binding.tvPlaceName.text = name
        Glide.with(holder.itemView.context)
            .load(img)
            .placeholder(R.drawable.ic_placeholder_photo)
            .error(R.drawable.ic_placeholder_photo)
            .into(holder.binding.ivPlace)
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listData[holder.adapterPosition]) }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dataTravel: List<TravelDataDummyResponse>) {
        listData.clear()
        listData.addAll(dataTravel)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TravelDataDummyResponse)
    }
}