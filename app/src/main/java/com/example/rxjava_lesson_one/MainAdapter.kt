package com.example.rxjava_lesson_one

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rxjava_lesson_one.databinding.ItemBinding

import io.reactivex.subjects.PublishSubject


class MainAdapter : ListAdapter<Data, MainAdapter.DataViewHolder>(DiffCallback()) {

    private val clickSubject = PublishSubject.create<String>()
    val clickEvent: PublishSubject<String> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    //TODO: Add check on null for @data in bind functions
    inner class DataViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {

            binding.textView.text = data.title
            binding.textView2.text = data.author
            Glide
                .with(binding.imageView.context)
                .load(data.url)
                .apply(
                    RequestOptions()
                        .error(R.drawable.image)
                )
                .into(binding.imageView)

            binding.executePendingBindings()


            //onClick by recycler item
            binding.root.setOnClickListener {
                clickSubject.onNext(data.title)
            }
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
