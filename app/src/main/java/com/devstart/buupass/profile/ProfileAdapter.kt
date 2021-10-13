package com.devstart.buupass.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devstart.buupass.R
import com.devstart.buupass.databinding.HireItemBinding
import com.devstart.buupass.profile.model.HireModel

class ProfileAdapter(private val context: Context) : ListAdapter<HireModel, ProfileAdapter.ProfileViewHolder>(ProfileDiffUtil) {

    inner class ProfileViewHolder(private val binding: HireItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HireModel) {
            binding.date.text = item.date
            Glide.with(binding.root).load(item.imageUrl)
                .placeholder(R.drawable.logo)
                .into(binding.carImage)
        }
        var status = binding.status

    }

    private fun getStatusColor(status: String, holder: ProfileViewHolder) {
        when(status) {
            "enquiry" -> {
                holder.status.setTextColor(ContextCompat.getColor(context, R.color.color_primary))
                holder.status.text = status.capitalize()
            }
            "returned" -> {
                holder.status.setTextColor(ContextCompat.getColor(context, R.color.color_green))
                holder.status.text = status.capitalize()
            }
            "onTransit" -> {
                holder.status.setTextColor(ContextCompat.getColor(context, R.color.color_secondary))
                holder.status.text = status.capitalize()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(HireItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val item = getItem(position)
        getStatusColor(item.status, holder)
        holder.bind(item)
    }

    object ProfileDiffUtil: DiffUtil.ItemCallback<HireModel>() {
        override fun areItemsTheSame(oldItem: HireModel, newItem: HireModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HireModel, newItem: HireModel): Boolean {
            return newItem == oldItem
        }

    }
}