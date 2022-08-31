package com.evapharma.animalhealth.applicationflow.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.applicationflow.domain.model.FeedModel
import com.evapharma.animalhealth.applicationflow.utils.FeedDiffUtils
import com.evapharma.animalhealth.databinding.FeedItemBinding

class FeedAdapter() : ListAdapter<FeedModel, FeedAdapter.FeedViewHolder>(FeedDiffUtils()) {

    class FeedViewHolder(val binding: FeedItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            FeedItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        val post = getItem(position)
        holder.binding.apply {
            titleOrText.text = post.text
            postImg.setImageBitmap(post.image)
            date.text = post.date
        }
    }
}