package com.example.brewdogbeer.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.brewdogbeer.data.BrewdogBeerItem
import com.example.brewdogbeer.databinding.BrewdogBeerListItemBinding

class BrewdogBeerAdapter(private val onClick: (BrewdogBeerItem) -> Unit) : ListAdapter<BrewdogBeerItem, BrewdogBeerAdapter.ViewHolder>(
    DiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BrewdogBeerListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: BrewdogBeerListItemBinding, val onClick: (BrewdogBeerItem) -> Unit) : RecyclerView.ViewHolder(binding.root){
        val image: ImageView = binding.image
        val name: TextView= binding.name
        val abv: TextView= binding.abv
        private var currentItem: BrewdogBeerItem? = null

        init {
            binding.root.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind(item: BrewdogBeerItem){
            currentItem = item
            Glide.with(binding.root.context).load(item.image_url).into(image)
            name.text = item.name
            abv.text = item.abv.toString()
        }
    }

    object DiffCallback:DiffUtil.ItemCallback<BrewdogBeerItem>(){
        override fun areItemsTheSame(oldItem: BrewdogBeerItem, newItem: BrewdogBeerItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BrewdogBeerItem,
            newItem: BrewdogBeerItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }
}