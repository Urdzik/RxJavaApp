package com.example.rxjava_lesson_one.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.rxjava_lesson_one.R
import com.example.rxjava_lesson_one.databinding.ItemBinding
import com.example.rxjava_lesson_one.model.Data

import io.reactivex.subjects.PublishSubject


class MainAdapter : ListAdapter<Data, MainAdapter.DataViewHolder>(
    DiffCallback()
) {

    private val clickSubject = PublishSubject.create<Data>()
    val clickEvent: PublishSubject<Data> = clickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class DataViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Data) {


            if (data.title != "" && data.title.isNotEmpty()) {
                binding.textView.text = data.title
            } else {
                binding.textView.text = "error"
            }
            if (data.author != "" && data.author.isNotEmpty()) {
                binding.textView2.text = data.author
            } else {
                binding.textView2.text = "error"
            }



            Glide
                .with(binding.imageView.context)
                .load(data.url)
                .apply(
                    RequestOptions()
                        .error(R.drawable.error)
                )
                .into(binding.imageView)




            binding.executePendingBindings()

            //onClick by recycler item
            binding.root.setOnClickListener {
                clickSubject.onNext(data)
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
