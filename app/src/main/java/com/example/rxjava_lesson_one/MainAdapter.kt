package com.example.rxjava_lesson_one

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjava_lesson_one.databinding.ItemBinding
import okhttp3.internal.Util

class MainAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Data, MainAdapter.DataViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(item)
        }
        holder.bind(item)
    }


    class DataViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.title == newItem.title
    }

}

class OnClickListener(val onClickListener: (data: Data) -> Util) {
    fun onClick(data: Data) = onClickListener(data)
}