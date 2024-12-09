package com.example.demoapp.adapters


import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.demoapp.App
import com.example.demoapp.AppStatus
import com.example.demoapp.R
import com.example.demoapp.databinding.ApplicationListItemBinding


class ApplicationsAdapter : ListAdapter<App,ApplicationsAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ApplicationListItemBinding = ApplicationListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        holder.binding.tvItemName.text = item.appName
        Glide.with(holder.itemView.context)
            .load(item.appIcon)
            .placeholder(R.drawable.baseline_broken_image_24)
            .into(holder.binding.ivItemImage)

        holder.binding.msItemSwitch.isChecked = item.status == AppStatus.ACTIVE
        if (item.status == AppStatus.ACTIVE) {
            holder.binding.msItemSwitch.thumbTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.color_green)
            holder.binding.msItemSwitch.trackTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.color_light_green)
        }
        else {
            holder.binding.msItemSwitch.thumbTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.color_grey)
            holder.binding.msItemSwitch.trackTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.color_light_grey)
        }
        holder.binding.msItemSwitch.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                item.status = AppStatus.ACTIVE
                holder.binding.msItemSwitch.thumbTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.color_green)
                holder.binding.msItemSwitch.trackTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.color_light_green)
            }
            else {
                item.status = AppStatus.INACTIVE
                holder.binding.msItemSwitch.thumbTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.color_grey)
                holder.binding.msItemSwitch.trackTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.color_light_grey)
            }
        }

    }



    class ViewHolder(_binding: ApplicationListItemBinding) : RecyclerView.ViewHolder(_binding.root) {
        var binding = _binding
    }


}

val DIFF_CALLBACK: DiffUtil.ItemCallback<App> = object : DiffUtil.ItemCallback<App>() {
    override fun areItemsTheSame(oldItem: App, newItem: App): Boolean {
        return oldItem.appId == newItem.appId
    }

    override fun areContentsTheSame(oldItem: App, newItem: App): Boolean {
        return oldItem == newItem
    }
}
