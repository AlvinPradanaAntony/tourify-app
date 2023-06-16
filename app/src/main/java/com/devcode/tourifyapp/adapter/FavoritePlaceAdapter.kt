package com.devcode.tourifyapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.data.local.entity.DestinationEntity
import com.devcode.tourifyapp.databinding.ItemsPlaceBinding
import com.devcode.tourifyapp.ui.detail.DetailActivity

class FavoritePlaceAdapter(private var listDestination: List<DestinationEntity>) :
    RecyclerView.Adapter<FavoritePlaceAdapter.ViewHolder>() {
    class ViewHolder(private var binding: ItemsPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DestinationEntity) {
            binding.apply {
                tvPlaceName.text = data.name
                tvPlaceDetail.text = data.address
                Glide.with(itemView.context)
                    .load(data.picture)
                    .placeholder(R.drawable.ic_placeholder_photo)
                    .error(R.drawable.ic_placeholder_photo)
                    .into(ivPlace)
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("id", data.id)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsPlaceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listDestination.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destination = listDestination[position]
        holder.bind(destination)
    }

    fun setData(newData: List<DestinationEntity>) {
        val diffUtil = diffCallback(this.listDestination, newData)
        val diffResult = DiffUtil.calculateDiff(diffUtil)

        this.listDestination = newData
        diffResult.dispatchUpdatesTo(this)
    }

    private fun diffCallback(
        oldList: List<DestinationEntity>,
        newList: List<DestinationEntity>
    ) = object : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].id == newList[newItemPosition].id

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition] === newList[newItemPosition]
    }
}