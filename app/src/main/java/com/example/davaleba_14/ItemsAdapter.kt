package com.example.davaleba_14

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.davaleba_14.dataClasses.ItemA
import com.example.davaleba_14.dataClasses.ItemB
import com.example.davaleba_14.databinding.ItemABinding
import com.example.davaleba_14.databinding.ItemBBinding

class ItemsAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(ItemDiffCallback()) {

    companion object {
        private const val VIEW_TYPE_A = 1
        private const val VIEW_TYPE_B = 2
    }


    private class ItemDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is ItemA && newItem is ItemA -> oldItem.id == newItem.id
                oldItem is ItemB && newItem is ItemB -> oldItem.id == newItem.id
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
            return when {
                oldItem is ItemA && newItem is ItemA -> {
                    oldItem.equals(newItem)
                }
                oldItem is ItemB && newItem is ItemB -> {
                    oldItem.equals(newItem)
                }
                else -> false
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_A -> {
                val binding = ItemABinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemAViewHolder(binding)
            }
            VIEW_TYPE_B -> {
                val binding = ItemBBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ItemBViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ItemAViewHolder -> {
                if (item is ItemA) {
                    holder.bind(item)
                }
            }
            is ItemBViewHolder -> {
                if (item is ItemB) {
                    holder.bind(item)
                }
            }
        }

    }

    class ItemAViewHolder(private val binding: ItemABinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemA: ItemA) {
            binding.apply {
                tvItemAName.text = itemA.name
                tvItemADescription.text = itemA.description
            }
        }
    }

    class ItemBViewHolder(private val binding: ItemBBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(itemB: ItemB) {
            binding.apply {
                tvItemBName.text = itemB.name
                tvItemBDescription.text = itemB.description
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is ItemA -> VIEW_TYPE_A
            is ItemB -> VIEW_TYPE_B
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

}