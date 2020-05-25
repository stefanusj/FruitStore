package com.stefanusj.fruitstore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.stefanusj.fruitstore.databinding.CartItemBinding
import com.stefanusj.fruitstore.model.FruitWithCart
import com.stefanusj.fruitstore.ui.fruit.FruitFragmentDirections


class CartAdapter : ListAdapter<FruitWithCart, CartAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        CartItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruitWithCart = getItem(position)
        holder.bind(fruitWithCart)
    }

    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.clearAnimation()
    }

    inner class ViewHolder(private val binding: CartItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.fruitWithCart?.let {
                    FruitFragmentDirections.toDetail(it.fruit.id)
                        .also(view.findNavController()::navigate)
                }
            }
        }

        fun bind(fruitWithCart: FruitWithCart) {

            binding.apply {
                this.fruitWithCart = fruitWithCart
                executePendingBindings()
            }
        }
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FruitWithCart>() {
            override fun areItemsTheSame(
                oldItem: FruitWithCart,
                newItem: FruitWithCart
            ): Boolean {
                return oldItem.fruit.id == newItem.fruit.id
            }

            override fun areContentsTheSame(
                oldItem: FruitWithCart,
                newItem: FruitWithCart
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}