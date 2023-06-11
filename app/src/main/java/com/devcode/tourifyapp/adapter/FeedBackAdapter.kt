package com.devcode.tourifyapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.data.remote.response.ReviewsResponse
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.databinding.ItemsReviewsBinding

class FeedBackAdapter(private val listData: ArrayList<ReviewsResponse>): RecyclerView.Adapter<FeedBackAdapter.ViewHolder>() {
    class ViewHolder(var binding: ItemsReviewsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FeedBackAdapter.ViewHolder(binding)
    }

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = listData[position].name
        val img = listData[position].photo_url
        val review = listData[position].review
        val score = listData[position].score
        Glide.with(holder.itemView.context)
            .load(img)
            .placeholder(R.drawable.ic_placeholder_photo)
            .error(R.drawable.ic_placeholder_photo)
            .into(holder.binding.ivPhoto)
        holder.binding.tvUsername.text = name
        holder.binding.tvReviews.text = review
        holder.binding.scoreRating.text = score.toString()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<ReviewsResponse>) {
        listData.clear()
        listData.addAll(data)
        notifyDataSetChanged()
    }
}