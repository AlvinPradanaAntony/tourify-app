package com.devcode.tourifyapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.data.local.entity.DestinationEntity
import com.devcode.tourifyapp.data.remote.response.ReviewsResponse
import com.devcode.tourifyapp.data.remote.response.TravelDataDummyResponse
import com.devcode.tourifyapp.databinding.ItemsReviewsBinding

class FeedBackAdapter(private var listData: List<ReviewsResponse>): RecyclerView.Adapter<FeedBackAdapter.ViewHolder>() {
    class ViewHolder(private var binding: ItemsReviewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ReviewsResponse) {
            binding.apply {
                tvUsername.text = data.name
                tvReviews.text = data.review
                scoreRating.text = data.score.toString()
                Glide.with(itemView.context)
                    .load(data.photo_url)
                    .placeholder(R.drawable.ic_placeholder_photo)
                    .error(R.drawable.ic_placeholder_photo)
                    .into(ivPhoto)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsReviewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val review = listData[position]
        holder.bind(review)
    }

    fun setData(newData: List<ReviewsResponse>) {
        val diffUtil = diffCallback(this.listData, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        this.listData = newData
        diffResult.dispatchUpdatesTo(this)
    }

    private fun diffCallback(
        oldList: List<ReviewsResponse>,
        newList: List<ReviewsResponse>
    ) = object : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].name == newList[newItemPosition].name

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]
    }
}