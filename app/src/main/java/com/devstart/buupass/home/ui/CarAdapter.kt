package com.devstart.buupass.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.devstart.buupass.R
import com.devstart.buupass.databinding.CarItemBinding
import com.devstart.buupass.home.model.CarModel

class CarAdapter : ListAdapter<CarModel, CarAdapter.CarViewHolder>(CarDiffUtil) {

    inner class CarViewHolder(private val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CarModel) {
            binding.title.text = item.title
            binding.hireType.text = item.hireType
            binding.amount.text = item.amount
            Glide.with(binding.root).load(item.imageUrl)
                .placeholder(R.drawable.no_image)
                .into(binding.imgTopDeals)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder(CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object CarDiffUtil: DiffUtil.ItemCallback<CarModel>() {
        override fun areItemsTheSame(oldItem: CarModel, newItem: CarModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CarModel, newItem: CarModel): Boolean {
            return newItem == oldItem
        }

    }
}