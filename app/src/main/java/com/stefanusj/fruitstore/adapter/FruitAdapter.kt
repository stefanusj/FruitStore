package com.stefanusj.fruitstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stefanusj.fruitstore.databinding.FruitItemBinding
import com.stefanusj.fruitstore.helper.getPlatformColor
import com.stefanusj.fruitstore.model.FruitWithProperty
import com.stefanusj.fruitstore.ui.fruit.FruitFragmentDirections


class FruitAdapter : ListAdapter<FruitWithProperty, FruitAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        FruitItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = getItem(position)
        holder.bind(fruit)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.clearAnimation()
    }

    inner class ViewHolder(private val binding: FruitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.fruitWithProperty?.let {
                    FruitFragmentDirections.toDetail(it.fruit.id)
                        .also(view.findNavController()::navigate)
                }
            }
        }

        fun bind(fruitWithProperty: FruitWithProperty) {

            getPlatformColor(binding.root.context, fruitWithProperty.fruit.image.small) {
                binding.cvFruit.setCardBackgroundColor(it)
            }

            binding.apply {
                this.fruitWithProperty = fruitWithProperty
                executePendingBindings()
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FruitWithProperty>() {
            override fun areItemsTheSame(
                oldItem: FruitWithProperty,
                newItem: FruitWithProperty
            ): Boolean {
                return oldItem.fruit.id == newItem.fruit.id
            }

            override fun areContentsTheSame(
                oldItem: FruitWithProperty,
                newItem: FruitWithProperty
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}